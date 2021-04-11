package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private FeignCacheService cacheService;

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
