package club.laky.sirius.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cache-service")
public interface FeignCacheService {

    @RequestMapping(value = "/cache/get", method = RequestMethod.GET)
    Object get(@RequestParam("key") String key);

    @RequestMapping(value = "/cache/set", method = RequestMethod.GET)
    Object set(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping(value = "/cache/setWithTime", method = RequestMethod.GET)
    Object setWithTime(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") long time);

    @RequestMapping(value = "/cache/del", method = RequestMethod.GET)
    Object del(@RequestParam("key") String key);
}
