package club.laky.sirius.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author prl
 * @Desrcription:
 * @date 2021/3/20 13:54
 */
@FeignClient(name = "ums-service")
public interface UmsServiceClient {

    /**
     * 获取登录用户的信息
     *
     * @param account
     * @return
     */
    @GetMapping("/permission/api/loginUser")
    String loginUser(@RequestParam(value = "account") String account);
}
