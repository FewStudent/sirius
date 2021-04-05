package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.service.LoginService;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("admin/admin/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;

    /**
     * 系统管理员登录
     */
    @ResponseBody
    @RequestMapping("login")
    public Object login(@RequestBody String jsonBody) {
        try {
            logger.info("-------------系统管理员登录-------------");
            return service.login(jsonBody);
        } catch (Exception e) {
            logger.error("系统管理员登录失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 系统管理员注销
     */
    @ResponseBody
    @RequestMapping("logout")
    public Object logout(@RequestParam String token) {
        try {
            logger.info("-------------系统管理员注销-------------");
            return service.login(token);
        } catch (Exception e) {
            logger.error("系统管理员注销失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

}
