package club.laky.sirius.admin.feign;

import club.laky.sirius.admin.utils.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "ums-service")
public interface FeignClientService {

    // 登录相关API
    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    Object logout(@RequestParam String token);

    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    Object login(@RequestParam("account") String account, @RequestParam("pwd") String pwd, @RequestParam("type") int type);

    //用户相关API
    @RequestMapping(value = "/api/manager/count", method = RequestMethod.GET)
    Integer queryAdminCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/manager/list", method = RequestMethod.GET)
    Object queryAdminList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/client/count", method = RequestMethod.GET)
    Integer queryClientCount(@RequestBody String toJSONString);

    @RequestMapping(value = "/api/client/list", method = RequestMethod.GET)
    Object queryClientList(@RequestBody String toJSONString);

    @RequestMapping(value = "/api/client/userList", method = RequestMethod.GET)
    Object queryUserList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/client/userCount", method = RequestMethod.GET)
    Integer queryUserCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/client/delete", method = RequestMethod.GET)
    Object delete(@RequestParam Integer userId);

    @RequestMapping(value = "/api/client/regist", method = RequestMethod.GET)
    Object regist(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/client/insertManager", method = RequestMethod.GET)
    Object insertManager(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/client/update", method = RequestMethod.GET)
    Integer update(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/client/updateUser", method = RequestMethod.GET)
    Integer updateUser(@RequestParam String jsonBody);

    @RequestMapping(value = "/api/user/detail", method = RequestMethod.GET)
    Object detail(@RequestParam String account, @RequestParam Integer type);


    //用户收货地址API
    @RequestMapping(value = "adminAddressCount", method = RequestMethod.GET)
    Integer adminAddressCount(@RequestParam String nickname);

    @RequestMapping(value = "adminAddressList", method = RequestMethod.GET)
    WebResult adminAddressList(@RequestParam String nickname, @RequestParam Integer page, @RequestParam Integer limit);

    @RequestMapping(value = "deleteAddress", method = RequestMethod.GET)
    Object deleteAddress(@RequestBody Integer addressId);

    @RequestMapping(value = "updateAddress", method = RequestMethod.GET)
    Object updateAddress(@RequestBody String jsonBody);

    @RequestMapping(value = "/addressDetail", method = RequestMethod.GET)
    Object addressDetail(@RequestParam Integer id);

    //用户商品收藏API
    @RequestMapping(value = "/deleteCollection", method = RequestMethod.GET)
    Object deleteCollection(@RequestParam Integer id);

    @RequestMapping(value = "/adminCollectionList", method = RequestMethod.GET)
    WebResult adminCollectionList(@RequestBody String jsonBody);

    @RequestMapping(value = "/adminCollectionCount", method = RequestMethod.GET)
    Integer adminCollectionCount(@RequestBody String jsonBody);

    //用户购物车API
}
