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


    /**
     * 商品详情
     */
    @RequestMapping("/common/detail")
    public ModelAndView productDetail(HttpServletRequest request, Integer goodsId) {
        ModelAndView result = modelUtils.createModel(request, "/detail");
        result.addObject("goodsId", goodsId);
        return result;
    }

    /**
     * 商品详情
     */
    @RequestMapping("/order/detail")
    public ModelAndView orderDetail(HttpServletRequest request, String orderNum) {
        ModelAndView result = modelUtils.createModel(request, "/order/detail");
        result.addObject("orderNum", orderNum);
        return result;
    }

    /**
     * 我的订单
     */
    @RequestMapping("order/myOrder")
    public ModelAndView myOrder(HttpServletRequest request) {
        return modelUtils.createModel(request, "/order/myOrder");
    }

    /**
     * 我的订单
     */
    @RequestMapping("order/newOrder")
    public ModelAndView newOrder(HttpServletRequest request, String jsonBody) {
        ModelAndView result = modelUtils.createModel(request, "/order/newOrder");
        result.addObject("jsonBody", jsonBody);
        return result;
    }

    /**
     * 用户地址
     */
    @RequestMapping("user/addressList")
    public ModelAndView addressList(HttpServletRequest request) {
        return modelUtils.createModel(request, "/user/addressList");
    }

    /**
     * 用户地址
     */
    @RequestMapping("user/userAddress")
    public ModelAndView userAddress(HttpServletRequest request) {
        return modelUtils.createModel(request, "/address/list");
    }

    /**
     * 修改地址
     */
    @RequestMapping("user/editAddress")
    public ModelAndView editAddress(HttpServletRequest request, Integer id) {
        ModelAndView result = modelUtils.createModel(request, "/address/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 修改地址
     */
    @RequestMapping("user/addAddress")
    public ModelAndView addAddress(HttpServletRequest request) {
        return modelUtils.createModel(request, "/address/add");
    }


    /**
     * 提交建议
     */
    @RequestMapping("suggestion")
    public ModelAndView suggestion(HttpServletRequest request) {
        return modelUtils.createModel(request, "/suggestion");
    }


    /**
     * 用户信息
     */
    @RequestMapping("user/edit")
    public ModelAndView userEdit(HttpServletRequest request) {
        return modelUtils.createModel(request, "/user/userInfo");
    }

    /**
     * 跳转到我的收藏页面
     */
    @RequestMapping("user/myCollect")
    public ModelAndView myCollect(HttpServletRequest request) {
        return modelUtils.createModel(request, "/collect/list");
    }
}
