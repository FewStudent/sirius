package club.laky.sirius.admin.feign;

import club.laky.sirius.admin.utils.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author panrulang
 */
@FeignClient(name = "pms-service")
public interface FeignGoodsService {

    @RequestMapping(value = "/api/goods/save", method = RequestMethod.GET)
    WebResult save(String jsonBody);

    @RequestMapping(value = "/api/goods/add", method = RequestMethod.GET)
    WebResult insert(String jsonBody);
}
