package club.laky.sirius.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/6/17 16:17
 */
@Controller
@RequestMapping("/api")
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    /**
     * 测试
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/test")
    public Object test(@RequestBody String jsonBody) {
        try {
            logger.info("-------------测试-------------");
            return jsonBody;
        } catch (Exception e) {
            logger.info("测试失败:{}", e.getMessage());
            return e.getMessage();
        }
    }
}
