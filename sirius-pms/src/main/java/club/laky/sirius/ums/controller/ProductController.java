package club.laky.sirius.ums.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {


    @GetMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", "ok");
        return map;
    }

    @GetMapping("/test2")
    public String test2(@RequestParam(name = "param1") String param1) {
        return param1;
    }

    @PostMapping("/test3")
    public Integer test3(Integer id) {
        return id;
    }
}
