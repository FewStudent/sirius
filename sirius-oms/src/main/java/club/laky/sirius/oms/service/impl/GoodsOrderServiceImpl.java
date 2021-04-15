package club.laky.sirius.oms.service.impl;

import club.laky.sirius.oms.constant.OrderState;
import club.laky.sirius.oms.dao.GoodsOrderListDao;
import club.laky.sirius.oms.entity.Goods;
import club.laky.sirius.oms.entity.GoodsOrder;
import club.laky.sirius.oms.dao.GoodsOrderDao;
import club.laky.sirius.oms.entity.GoodsOrderList;
import club.laky.sirius.oms.feign.FeignGoodsService;
import club.laky.sirius.oms.service.GoodsOrderService;
import club.laky.sirius.oms.utils.WebResult;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (GoodsOrder)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-15 22:14:46
 */
@Service("goodsOrderService")
public class GoodsOrderServiceImpl implements GoodsOrderService {
    @Resource
    private GoodsOrderDao goodsOrderDao;

    @Resource
    private GoodsOrderListDao goodsOrderListDao;

    @Autowired
    private FeignGoodsService goodsService;

    private static final Logger logger = LoggerFactory.getLogger(GoodsOrderListServiceImpl.class);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsOrder queryById(Integer id) {
        return this.goodsOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsOrder> queryAllByLimit(int offset, int limit) {
        return this.goodsOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsOrder 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsOrder insert(GoodsOrder goodsOrder) {
        this.goodsOrderDao.insert(goodsOrder);
        return goodsOrder;
    }

    /**
     * 修改数据
     *
     * @param goodsOrder 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsOrder update(GoodsOrder goodsOrder) {
        this.goodsOrderDao.update(goodsOrder);
        return this.queryById(goodsOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsOrderDao.deleteById(id) > 0;
    }

    @Override
    public WebResult saveOrder(String jsonBody, Integer userId, Integer addressId) {
        JSONArray orderList = JSONObject.parseArray(jsonBody);
        List<String> ids = new ArrayList<>();
        List<GoodsOrderList> goodsOrderLists = new ArrayList<>();
        for (Object o : orderList) {
            JSONObject object = (JSONObject) o;
            GoodsOrderList goodsOrderList = new GoodsOrderList();
            Integer goodsId = object.getInteger("goodsId");
            goodsOrderList.setGoodsCount(object.getInteger("count"));
            goodsOrderList.setGoodsCount(goodsId);
            goodsOrderLists.add(goodsOrderList);
            ids.add(goodsId + "");
        }
        int count = goodsOrderDao.queryGoodsCount(ids);
        if (ids.size() != count) {
            logger.error("生成订单失败,商品不存在");
            return WebResult.error("生成订单失败,商品不存在!");
        }

        //校验库存
        StringBuilder idParams = new StringBuilder();
        for (String id : ids) {
            idParams.append(id);
        }
        List<Goods> goodsList = goodsService.getGoodsByIds(idParams.toString());
        for (GoodsOrderList goodsOrderList : goodsOrderLists) {
            for (Goods goods : goodsList) {
                if (goods.getId().equals(goodsOrderList.getGoodsId())) {
                    if (goods.getCount() < goodsOrderList.getGoodsCount()) {
                        return WebResult.error("库存不足,无法下单");
                    }
                }
            }
        }

        //生成订单
        logger.info("订单生成中............");
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setAddressId(addressId);
        goodsOrder.setCreateTime(DateUtil.now());
        goodsOrder.setUId(userId);
        goodsOrder.setState(0);
        goodsOrder.setOrderNum(UUID.randomUUID().toString().replace("-", "").substring(0, 20));
        if (goodsOrderDao.insert(goodsOrder) != 1) {
            return WebResult.error("生成订单失败");
        }
        int result = goodsOrderListDao.batchInsert(goodsOrderLists);
        if (result == count) {
            WebResult.success(goodsOrder.getOrderNum());
        }
        return WebResult.error("订单生成失败");
    }

    @Override
    public WebResult orderDetail(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryOrderDetail(orderId);
        if (order == null) {
            return WebResult.error("获取失败");
        }
        return WebResult.success(order);
    }

    @Override
    public WebResult getMyOrders(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer state = params.getInteger("state");
        Integer userId = params.getInteger("userId");
        Integer page = params.getInteger("page");
        Integer limit = params.getInteger("limit");

        List<GoodsOrder> orderList = goodsOrderDao.queryMyOrder(userId, state, page, limit);
        Integer count = goodsOrderDao.queryMyOrderCount(userId, state);
        Map<String, Object> result = new HashMap<>();
        result.put("list", orderList);
        result.put("count", count);
        return WebResult.success(result);
    }

    @Override
    public WebResult getAdminOrders(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer state = params.getInteger("state");
        String orderNum = params.getString("orderNum");
        String nickname = params.getString("nickname");
        Integer page = params.getInteger("page");
        Integer limit = params.getInteger("limit");

        List<GoodsOrder> orderList = goodsOrderDao.queryAdminOrders(orderNum, state, nickname, page, limit);
        Integer count = goodsOrderDao.queryAdminOrdersCount(orderNum, state, nickname);
        Map<String, Object> result = new HashMap<>();
        result.put("list", orderList);
        result.put("count", count);
        return WebResult.success(result);
    }

    @Override
    public WebResult checkSendOrder(Integer orderId) {
        //TODO 发货校验
        return null;
    }

    @Override
    public WebResult cancelOrder(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryById(orderId);
        if (order.getState() != OrderState.START) {
            return WebResult.error("订单不是<下单>状态,取消订单失败");
        }
        order.setState(OrderState.CANCEL);
        int result = goodsOrderDao.update(order);
        if (result == 1) {
            return WebResult.success("订单取消成功");
        }
        return WebResult.error("订单取消失败");
    }

    @Override
    public WebResult checkTakeOrder(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryById(orderId);
        if (order.getState() != OrderState.SEND) {
            return WebResult.error("订单不是<发货中>状态,取消订单失败");
        }
        order.setState(OrderState.TAKE);
        int result = goodsOrderDao.update(order);
        if (result == 1) {
            return WebResult.success("订单发货成功");
        }
        return WebResult.error("订单发货失败");
    }

    @Override
    public WebResult closeOrder(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryById(orderId);
        if (order.getState() != OrderState.TAKE) {
            return WebResult.error("订单不是<确认收货>状态,取消订单失败");
        }
        order.setState(OrderState.END);
        int result = goodsOrderDao.update(order);
        if (result == 1) {
            return WebResult.success("订单完结成功");
        }
        return WebResult.error("订单完结失败");
    }
}