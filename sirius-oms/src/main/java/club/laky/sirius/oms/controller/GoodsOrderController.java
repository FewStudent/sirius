package club.laky.sirius.oms.controller;

import club.laky.sirius.oms.entity.GoodsOrder;
import club.laky.sirius.oms.service.GoodsOrderService;
import club.laky.sirius.oms.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GoodsOrder)表控制层
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:37
 */
@RestController
@RequestMapping("goodsOrder")
public class GoodsOrderController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsOrderController.class);

    /**
     * 服务对象
     */
    @Resource
    private GoodsOrderService goodsOrderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GoodsOrder selectOne(Integer id) {
        return this.goodsOrderService.queryById(id);
    }

    /**
     * 保存订单
     */
    @ResponseBody
    @RequestMapping("saveOrder")
    public Object saveOrder(@RequestParam String jsonBody, @RequestParam Integer userId, Integer addressId) {
        try {
            logger.info("-------------保存订单-------------");
            return goodsOrderService.saveOrder(jsonBody, userId, addressId);
        } catch (Exception e) {
            logger.error("保存订单失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 订单详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object orderDetail(@RequestParam Integer orderId) {
        try {
            logger.info("-------------订单详情-------------");
            return goodsOrderService.orderDetail(orderId);
        } catch (Exception e) {
            logger.error("订单详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 获取我的订单
     */
    @ResponseBody
    @RequestMapping("getMyOrders")
    public Object getMyOrders(@RequestParam String jsonBody) {
        try {
            logger.info("-------------获取我的订单-------------");
            return goodsOrderService.getMyOrders(jsonBody);
        } catch (Exception e) {
            logger.error("获取我的订单失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 后台管理订单
     */
    @ResponseBody
    @RequestMapping("getAdminOrders")
    public Object getAdminOrders(@RequestParam String jsonBody) {
        try {
            logger.info("-------------后台管理订单-------------");
            return goodsOrderService.getAdminOrders(jsonBody);
        } catch (Exception e) {
            logger.error("后台管理订单失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 确认发货
     */
    @ResponseBody
    @RequestMapping("checkSendOrder")
    public Object checkSendOrder(@RequestBody Integer orderId) {
        try {
            logger.info("-------------确认发货-------------");
            return goodsOrderService.checkSendOrder(orderId);
        } catch (Exception e) {
            logger.error("确认发货失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 取消订单
     */
    @ResponseBody
    @RequestMapping("cancelOrder")
    public Object cancelOrder(@RequestBody Integer orderId) {
        try {
            logger.info("-------------取消订单-------------");
            return goodsOrderService.cancelOrder(orderId);
        } catch (Exception e) {
            logger.error("取消订单失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 确认收货
     */
    @ResponseBody
    @RequestMapping("checkTakeOrder")
    public Object checkTakeOrder(@RequestBody Integer orderId) {
        try {
            logger.info("-------------确认收货-------------");
            return goodsOrderService.checkTakeOrder(orderId);
        } catch (Exception e) {
            logger.error("确认收货失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 订单完结
     */
    @ResponseBody
    @RequestMapping("closeOrder")
    public Object closeOrder(@RequestBody Integer orderId) {
        try {
            logger.info("-------------订单完结-------------");
            return goodsOrderService.closeOrder(orderId);
        } catch (Exception e) {
            logger.error("订单完结失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

}