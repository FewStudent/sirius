package club.laky.sirius.client.feign;

import club.laky.sirius.client.utils.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "ums-service")
public interface FeignClientService {

    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    Object logout(@RequestParam String token);

    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    Object login(@RequestParam("account") String account, @RequestParam("pwd") String pwd, @RequestParam("type") int type);

    @RequestMapping(value = "/api/manager/count", method = RequestMethod.GET)
    Integer queryAdminCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/manager/list", method = RequestMethod.GET)
    Object queryAdminList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/client/count", method = RequestMethod.GET)
    Integer queryClientCount(@RequestBody String toJSONString);

    @RequestMapping(value = "/api/client/list", method = RequestMethod.GET)
    Object queryClientList(@RequestBody String toJSONString);

    @RequestMapping(value = "/api/client/regist", method = RequestMethod.GET)
    Object regist(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/client/updateUser", method = RequestMethod.GET)
    Integer updateUser(@RequestParam String jsonBody);

    /**
     * 地址
     */
    @RequestMapping(value = "/api/address/userAddressList", method = RequestMethod.GET)
    Object userAddressList(@RequestParam Integer userId);

    @RequestMapping(value = "/api/address/updateAddress", method = RequestMethod.GET)
    Object updateAddress(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/address/deleteAddress", method = RequestMethod.GET)
    Object deleteAddress(@RequestBody Integer addressId);

    @RequestMapping(value = "/api/address/insertAddress", method = RequestMethod.GET)
    Object insertAddress(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/address/addressDetail", method = RequestMethod.GET)
    Object addressDetail(@RequestParam Integer id);

    @RequestMapping(value = "/api/collection/hasCollect", method = RequestMethod.GET)
    Object hasCollect(@RequestParam Integer userId, @RequestParam Integer goodsId);

    @RequestMapping(value = "/api/collection/insertCollection", method = RequestMethod.GET)
    Object insertCollection(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/collection/deleteCollection", method = RequestMethod.GET)
    Object deleteCollection(@RequestParam Integer id);

    @RequestMapping(value = "/api/collection/collections", method = RequestMethod.GET)
    Object collections(@RequestParam Integer userId);

    @RequestMapping(value = "/api/collection/cancelCollection", method = RequestMethod.GET)
    Object cancelCollection(@RequestParam Integer goodsId, @RequestParam Integer userId);

    @RequestMapping(value = "/api/cart/clearCart", method = RequestMethod.GET)
    WebResult clearCart(@RequestParam String cartIds);

    @RequestMapping("/api/cart/insertCart")
    Object insertCart(@RequestParam String jsonBody);

    @RequestMapping("/api/cart/deleteCart")
    Object deleteCart(@RequestParam Integer id);

    @RequestMapping("/api/cart/userCartList")
    Object userCartList(@RequestParam Integer userId);
}
