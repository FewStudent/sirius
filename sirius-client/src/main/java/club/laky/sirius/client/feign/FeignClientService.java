package club.laky.sirius.client.feign;

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
    Object regist(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/client/updateUser", method = RequestMethod.GET)
    Integer updateUser(@RequestParam String jsonBody);

    /**
     * 地址
     */
    @RequestMapping(value = "/api/address/userAddressList", method = RequestMethod.GET)
    Object userAddressList(@RequestParam Integer userId);

    @RequestMapping(value = "/api/address/update", method = RequestMethod.GET)
    Object update(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/address/delete", method = RequestMethod.GET)
    Object delete(@RequestBody Integer addressId);

    @RequestMapping(value = "/api/address/insert", method = RequestMethod.GET)
    Object insert(@RequestBody String jsonBody);


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
}
