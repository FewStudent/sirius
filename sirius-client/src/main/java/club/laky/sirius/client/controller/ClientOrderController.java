package club.laky.sirius.client.controller;

import club.laky.sirius.client.entity.SysUser;
import club.laky.sirius.client.feign.FeignCacheService;
import club.laky.sirius.client.feign.FeignClientService;
import club.laky.sirius.client.feign.FeignOrderService;
import club.laky.sirius.client.utils.WebResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/client/order")
public class ClientOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ClientOrderController.class);
    @Autowired
    private FeignOrderService orderService;
    @Autowired
    private FeignCacheService cacheService;
    @Autowired
    private FeignClientService clientService;

    /**
     * 保存订单
     */
    @ResponseBody
    @RequestMapping("saveOrder")
    public Object saveOrder(HttpServletRequest request, @RequestBody String jsonBody) {
        try {
            logger.info("-------------保存订单-------------");

            JSONObject object = JSONObject.parseObject(jsonBody);
            object.put("userId", getUserId(request));
            return orderService.saveOrder(object.toJSONString());
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
    public Object detail(@RequestParam String orderNum) {
        try {
            logger.info("-------------订单详情-------------");
            return orderService.orderDetail(orderNum);
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
    public Object getMyOrders(HttpServletRequest request, @RequestBody String jsonBody) {
        try {
            logger.info("-------------获取我的订单-------------");
            JSONObject object = JSONObject.parseObject(jsonBody);
            object.put("userId", getUserId(request));
            return orderService.getMyOrders(object.toJSONString());
        } catch (Exception e) {
            logger.error("获取我的订单失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 取消订单
     */
    @ResponseBody
    @RequestMapping("cancelOrder")
    public Object cancelOrder(@RequestParam Integer orderId) {
        try {
            logger.info("-------------取消订单-------------");
            return orderService.cancelOrder(orderId);
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
    public Object checkTakeOrder(@RequestParam Integer orderId) {
        try {
            logger.info("-------------确认收货-------------");
            return orderService.checkTakeOrder(orderId);
        } catch (Exception e) {
            logger.error("确认收货失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 从购物车购物
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/buy")
    public Object buy(@RequestBody String jsonBody) {
        try {
            logger.info("-------------从购物车购物-------------");
            JSONObject params = JSON.parseObject(jsonBody);
            JSONArray orderList = params.getJSONArray("list");
            String ids = "";
            for (Object o : orderList) {
                JSONObject jsonObject = (JSONObject) o;
                ids += jsonObject.getInteger("id") + ",";
            }
            //清理购物车
            WebResult webResult = clientService.clearCart(ids);
            if (!webResult.isSucceed()) {
                return webResult;
            }
            //保存订单
            return orderService.saveOrder(jsonBody);
        } catch (Exception e) {
            logger.info("从购物车购物失败:{}", e.getMessage());
            return WebResult.error("从购物车购物失败");
        }
    }

    private Integer getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            Map<String, Object> data = (Map<String, Object>) cacheService.get(token);
            SysUser user = JSON.parseObject((String) data.get("data"), SysUser.class);
            return user.getId();
        }
        return null;
    }
}
