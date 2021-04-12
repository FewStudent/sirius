package club.laky.sirius.client.controller;

import club.laky.sirius.client.feign.FeignClientService;
import club.laky.sirius.client.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/client/common")
public class ClientLoginController {

    private static final Logger logger = LoggerFactory.getLogger(ClientLoginController.class);


    @Autowired
    private FeignClientService clientService;

    /**
     * 系统会员登录
     */
    @ResponseBody
    @RequestMapping("login")
    public Object login(@RequestBody String jsonBody) {
        try {
            logger.info("-------------系统会员登录-------------");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody);
            String account = jsonObject.getString("account");
            String pwd = jsonObject.getString("pwd");
            return clientService.login(account, pwd, 1);
        } catch (Exception e) {
            logger.error("系统会员登录失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 系统会员注销
     */
    @ResponseBody
    @RequestMapping("logout")
    public Object logout(@RequestParam String token) {
        try {
            logger.info("-------------系统会员注销-------------");
            return clientService.logout(token);
        } catch (Exception e) {
            logger.error("系统会员注销失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 注册
     */
    @ResponseBody
    @RequestMapping("regist")
    public Object regist(@RequestBody String jsonBody) {
        try {
            logger.info("-------------注册-------------");
            return clientService.regist(jsonBody);
        } catch (Exception e) {
            logger.error("注册失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    //TODO 通过邮件获取验证码


}