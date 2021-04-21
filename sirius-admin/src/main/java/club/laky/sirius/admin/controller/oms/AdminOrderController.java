package club.laky.sirius.admin.controller.oms;

import club.laky.sirius.admin.feign.FeignOrderService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

    @Autowired
    private FeignOrderService orderService;


    /**
     * 订单列表
     */
    @ResponseBody
    @RequestMapping("list")
    public Object list(Integer page, Integer limit, Integer state, String orderNum, String nickname) {
        try {
            logger.info("-------------订单列表-------------");
            JSONObject object = new JSONObject();
            object.put("page", page);
            object.put("limit", limit);
            object.put("state", state);
            object.put("orderNum", orderNum);
            object.put("nickname", nickname);
            Map<String, Object> result = (Map<String, Object>) orderService.getAdminOrders(object.toJSONString());
            Map<String, Object> data = (Map<String, Object>) result.get("data");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount((Integer) data.get("count"));
            layData.setData((List) data.get("list"));
            return layData;
        } catch (Exception e) {
            logger.error("订单列表失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 订单完结
     */
    @ResponseBody
    @RequestMapping("closeOrder")
    public Object closeOrder(@RequestParam Integer orderId) {
        try {
            logger.info("-------------订单完结-------------");
            return orderService.closeOrder(orderId);
        } catch (Exception e) {
            logger.error("订单完结失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 订单发货
     */
    @ResponseBody
    @RequestMapping("checkSendOrder")
    public Object checkSendOrder(@RequestParam Integer orderId) {
        try {
            logger.info("-------------订单发货-------------");
            return orderService.checkSendOrder(orderId);
        } catch (Exception e) {
            logger.error("订单发货失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 订单详情
     */
    @ResponseBody
    @RequestMapping("orderDetail")
    public Object orderDetail(@RequestParam String orderNum) {
        try {
            logger.info("-------------订单详情-------------");
            return orderService.orderDetail(orderNum);
        } catch (Exception e) {
            logger.error("订单详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}
