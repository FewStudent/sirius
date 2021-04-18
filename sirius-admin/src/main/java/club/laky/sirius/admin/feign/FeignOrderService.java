package club.laky.sirius.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oms-service")
public interface FeignOrderService {

    @RequestMapping(value = "/api/order/saveOrder", method = RequestMethod.GET)
    Object saveOrder(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/order/detail", method = RequestMethod.GET)
    Object orderDetail(@RequestParam String orderNum);

    @RequestMapping(value = "/api/order/getMyOrders", method = RequestMethod.GET)
    Object getMyOrders(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/order/getAdminOrders",method = RequestMethod.GET)
    Object getAdminOrders(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/order/checkSendOrder", method = RequestMethod.GET)
    Object checkSendOrder(@RequestParam Integer orderId);

    @RequestMapping(value = "/api/order/cancelOrder", method = RequestMethod.GET)
    Object cancelOrder(@RequestParam Integer orderId);

    @RequestMapping(value = "/api/order/checkTakeOrder", method = RequestMethod.GET)
    Object checkTakeOrder(@RequestParam Integer orderId);

    @RequestMapping(value = "/api/order/closeOrder", method = RequestMethod.GET)
    Object closeOrder(@RequestParam Integer orderId);

}
