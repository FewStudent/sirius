package club.laky.sirius.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    /**
     * 随便整点
     */
    @ResponseBody
    @RequestMapping("ok")
    public Object ok() {
        try {
            logger.info("-------------随便整点-------------");
            return "还行";
        } catch (Exception e) {
            logger.error("随便整点失败：" + e.getMessage());
            return null;
        }
    }
}
