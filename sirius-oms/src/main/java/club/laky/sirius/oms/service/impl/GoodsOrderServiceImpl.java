package club.laky.sirius.oms.service.impl;

import club.laky.sirius.oms.constant.OrderState;
import club.laky.sirius.oms.dao.GoodsOrderListDao;
import club.laky.sirius.oms.entity.Goods;
import club.laky.sirius.oms.entity.GoodsOrder;
import club.laky.sirius.oms.dao.GoodsOrderDao;
import club.laky.sirius.oms.entity.GoodsOrderList;
import club.laky.sirius.oms.feign.FeignGoodsService;
import club.laky.sirius.oms.provider.MailCreator;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MailCreator mailCreator;

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
    @Transactional
    public WebResult saveOrder(String jsonBody) {
        JSONObject params = JSON.parseObject(jsonBody);
        Integer addressId = params.getInteger("addressId");
        Integer userId = params.getInteger("userId");
        JSONArray orderList = params.getJSONArray("list");
        List<String> ids = new ArrayList<>();
        List<GoodsOrderList> goodsOrderLists = new ArrayList<>();
        for (Object o : orderList) {
            JSONObject object = (JSONObject) o;
            GoodsOrderList goodsOrderList = new GoodsOrderList();
            Integer goodsId = object.getInteger("goodsId");
            goodsOrderList.setGoodsCount(object.getInteger("count"));
            goodsOrderList.setGoodsId(goodsId);
            goodsOrderLists.add(goodsOrderList);
            ids.add(goodsId + "");
        }
        int count = goodsOrderDao.queryGoodsCount(ids);
        if (ids.size() != count) {
            logger.error("生成订单失败,商品不存在");
            throw new RuntimeException("生成订单失败,商品不存在!");
        }

        //校验库存
        StringBuilder idParams = new StringBuilder();
        for (String id : ids) {
            idParams.append(id).append(",");
        }
        List<Goods> goodsList = goodsService.getGoodsByIds(idParams.toString());
        for (GoodsOrderList goodsOrderList : goodsOrderLists) {
            for (Goods goods : goodsList) {
                if (goods.getId().equals(goodsOrderList.getGoodsId())) {
                    if (goods.getCount() < goodsOrderList.getGoodsCount()) {
                        logger.error("库存不足,无法下单!");
                        throw new RuntimeException("库存不足,无法下单!");
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
        goodsOrder.setOrderNum("TL-" + UUID.randomUUID().toString().replace("-", "").substring(0, 20));
        if (goodsOrderDao.insert(goodsOrder) != 1) {
            logger.error("订单生成失败");
            throw new RuntimeException("生成订单失败!");
        }
        int result = goodsOrderListDao.batchInsert(goodsOrderLists, goodsOrder.getId());
        if (result != count) {
            logger.error("订单生成失败");
            throw new RuntimeException("订单生成失败!");
        }
        goodsOrder = goodsOrderDao.queryById(goodsOrder.getId());
        JSONObject object = new JSONObject();
        object.put("email", goodsOrder.getEmail());
        object.put("title", "订单生成");
        object.put("content", "订单<" + goodsOrder.getOrderNum() + ">已生成!");
        try {
            mailCreator.send(object.toJSONString(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResult.success(goodsOrder.getOrderNum());
    }

    @Override
    public WebResult orderDetail(String orderNum) {
        GoodsOrder order = goodsOrderDao.queryOrderDetail(orderNum);
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
        if (state == -1) {
            state = null;
        }

        List<GoodsOrder> orderList = goodsOrderDao.queryMyOrder(userId, state, (page - 1) * limit, limit);
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

        List<GoodsOrder> orderList = goodsOrderDao.queryAdminOrders(orderNum, state, nickname, (page - 1) * limit, limit);
        Integer count = goodsOrderDao.queryAdminOrdersCount(orderNum, state, nickname);
        Map<String, Object> result = new HashMap<>();
        result.put("list", orderList);
        result.put("count", count);
        return WebResult.success(result);
    }

    @Override
    @Transactional
    public WebResult checkSendOrder(Integer orderId) {
        //TODO 发货校验
        List<GoodsOrderList> goodsOrderLists = goodsOrderListDao.queryByOrderId(orderId);
        StringBuilder idParams = new StringBuilder();
        for (GoodsOrderList goodsOrderList : goodsOrderLists) {
            idParams.append(goodsOrderList.getGoodsId());
        }
        List<Goods> goodsList = goodsService.getGoodsByIds(idParams.toString());
        for (GoodsOrderList goodsOrderList : goodsOrderLists) {
            for (Goods goods : goodsList) {
                if (goods.getId().equals(goodsOrderList.getGoodsId())) {
                    if (goods.getCount() < goodsOrderList.getGoodsCount()) {
                        logger.error("库存不足,无法发货");
                        throw new RuntimeException("库存不足,无法发货");
                    }
                }
            }
        }
        //库存变更
        if (goodsOrderListDao.goodsChange(orderId) == 0) {
            logger.error("商品出库失败!");
            throw new RuntimeException("商品出库失败!");
        }

        GoodsOrder order = goodsOrderDao.queryById(orderId);
        order.setState(OrderState.SEND);
        if (goodsOrderDao.update(order) == 0) {
            logger.error("订单变更失败!");
            throw new RuntimeException("订单变更失败!!");
        }
        JSONObject object = new JSONObject();
        object.put("email", order.getEmail());
        object.put("title", "订单发货通知");
        object.put("content", "订单<" + order.getOrderNum() + ">已发货!");
        try {
            mailCreator.send(object.toJSONString(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResult.success("已发货!");
    }

    @Override
    public WebResult cancelOrder(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryById(orderId);
        if (order == null) {
            logger.error("订单不存在!");
            throw new RuntimeException("订单不存在");
        }
        if (!order.getState().equals(OrderState.START)) {
            logger.error("订单不是<下单>状态,取消订单失败");
            throw new RuntimeException("订单不是<下单>状态,取消订单失败");
        }
        order.setState(OrderState.CANCEL);
        int result = goodsOrderDao.update(order);
        if (result == 0) {
            logger.error("订单取消失败");
            throw new RuntimeException("订单取消失败!");
        }
        JSONObject object = new JSONObject();
        object.put("email", order.getEmail());
        object.put("title", "订单取消");
        object.put("content", "订单<" + order.getOrderNum() + ">已取消!");
        try {
            mailCreator.send(object.toJSONString(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResult.success("订单取消成功");
    }

    @Override
    public WebResult checkTakeOrder(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryById(orderId);
        if (!order.getState().equals(OrderState.SEND)) {
            return WebResult.error("订单不是<发货中>状态,确认收货失败");
        }
        order.setState(OrderState.TAKE);
        int result = goodsOrderDao.update(order);
        if (result == 1) {
            return WebResult.success("订单收货成功");
        }
        return WebResult.error("订单收货失败");
    }

    @Override
    public WebResult closeOrder(Integer orderId) {
        GoodsOrder order = goodsOrderDao.queryById(orderId);
        if (!order.getState().equals(OrderState.TAKE)) {
            return WebResult.error("订单不是<确认收货>状态,订单完结失败");
        }
        order.setState(OrderState.END);
        int result = goodsOrderDao.update(order);
        if (result == 1) {
            return WebResult.success("订单完结成功");
        }
        return WebResult.error("订单完结失败");
    }
}