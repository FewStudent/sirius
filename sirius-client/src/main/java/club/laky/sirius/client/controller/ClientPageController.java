package club.laky.sirius.client.controller;

import club.laky.sirius.client.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/client/page")
public class ClientPageController {

    @Autowired
    ModelUtils modelUtils;

    /**
     * 跳转到首页
     */
    @RequestMapping("/common/index")
    public ModelAndView index(HttpServletRequest request) {
        return modelUtils.createModel(request, "/index");
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/common/login")
    public ModelAndView login(HttpServletRequest request) {
        return modelUtils.createModel(request, "/login");
    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping("/common/regist")
    public ModelAndView regist(HttpServletRequest request) {
        return modelUtils.createModel(request, "/regist");
    }

}
