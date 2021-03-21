package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.client.FeignCacheService;
import club.laky.sirius.ums.client.FeignTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignTestController {

    @Resource
    private FeignTestService feignTestService;

    @Resource
    private FeignCacheService cacheService;

    @GetMapping("/test")
    public String test() {
        return feignTestService.test().toString();
    }

    @GetMapping("/test2")
    public ResponseEntity test2() {
        return ResponseEntity.ok(cacheService.get("k1"));
    }

    @GetMapping("/test3")
    public ResponseEntity test3() {
        Integer test3 = feignTestService.test3(1);
        return ResponseEntity.ok(test3);
    }
}