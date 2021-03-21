package club.laky.sirius.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 10044
 */
@Controller
@RequestMapping("/ok")
public class TestController {

    @ResponseBody
    @RequestMapping("ok")
    public Object ok() {
        return "ok";
    }
}
