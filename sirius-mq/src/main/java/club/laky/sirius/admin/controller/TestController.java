package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.receiver.MailCreator;
import club.laky.sirius.admin.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 10044
 */
@Controller
@RequestMapping("/ok")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private MailCreator mailCreator;

    @ResponseBody
    @RequestMapping("ok")
    public Object ok() {
        mailService.sendCodeMail("1047904478@qq.com", "假的,加了特技", "7968");
        return "很好,很有精神";
    }

    /**
     * 获得验证码
     */
    @ResponseBody
    @RequestMapping("code")
    public Object code() {
        try {
            logger.info("-------------获得验证码-------------");
            mailCreator.send("好家伙", null);
            return "还行";
        } catch (Exception e) {
            logger.error("获得验证码失败：" + e.getMessage());
            return e.getMessage();
        }
    }
}
