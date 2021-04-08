package club.laky.sirius.admin.feign;

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
}
