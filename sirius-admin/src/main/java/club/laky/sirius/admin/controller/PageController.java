package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.utils.ModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/page")
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private ModelUtils modelUtils;

    /**
     * 登录
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        return new ModelAndView("/login");
    }

    /**
     * 首页
     */
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return modelUtils.createModel(request, "/index");
    }

    /**
     * 后台用户管理
     */
    @RequestMapping("manager/index")
    public ModelAndView userIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/manager/index");
    }

    /**
     * 角色管理
     */
    @RequestMapping("role/index")
    public ModelAndView roleIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/role/index");
    }

    /**
     * 权限管理
     */
    @RequestMapping("permission/index")
    public ModelAndView permissionIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/permission/index");
    }

    /**
     * 部门管理
     */
    @RequestMapping("department/index")
    public ModelAndView departmentIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/department/index");
    }

    /**
     * 岗位管理
     */
    @RequestMapping("job/index")
    public ModelAndView jobIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/job/index");
    }

    /**
     * 登录日志管理
     */
    @RequestMapping("loginLog/index")
    public ModelAndView loginLogIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/loginLog/index");
    }

    /**
     * 图片上传记录管理
     */
    @RequestMapping("imgLog/index")
    public ModelAndView imgLogIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/imgLog/index");
    }

    /**
     * 客户管理
     */
    @RequestMapping("client/index")
    public ModelAndView clientIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/client/index");
    }


    /**
     * 用户统计
     */
    @RequestMapping("client/stats")
    public ModelAndView clientStats(HttpServletRequest request) {
        return modelUtils.createModel(request, "/client/stats");
    }

    /**
     * 客户购物车管理
     */
    @RequestMapping("cart/index")
    public ModelAndView cartIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/cart/index");
    }

    /**
     * 客户收货地址管理
     */
    @RequestMapping("address/index")
    public ModelAndView addressIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/address/index");
    }

    /**
     * 意见反馈管理
     */
    @RequestMapping("suggestion/index")
    public ModelAndView suggestionIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/suggestion/index");
    }


    /**
     * 发货订单管理
     */
    @RequestMapping("order/start")
    public ModelAndView orderStart(HttpServletRequest request) {
        return modelUtils.createModel(request, "/order/start");
    }


    /**
     * 发货中的管理
     */
    @RequestMapping("order/processing")
    public ModelAndView orderProcessing(HttpServletRequest request) {
        return modelUtils.createModel(request, "/order/processing");
    }


    /**
     * 完结订单
     */
    @RequestMapping("order/end")
    public ModelAndView orderEnd(HttpServletRequest request) {
        return modelUtils.createModel(request, "/order/end");
    }

    /**
     * 订单统计
     */
    @RequestMapping("order/stats")
    public ModelAndView orderStats(HttpServletRequest request) {
        return modelUtils.createModel(request, "/order/stats");
    }


    /**
     * 商品列表
     */
    @RequestMapping("product/index")
    public ModelAndView productIndex(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/index");
    }

    /**
     * 商品上架
     */
    @RequestMapping("product/sale")
    public ModelAndView productSale(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/sale");
    }

    /**
     * 商品上架
     */
    @RequestMapping("product/detail")
    public ModelAndView productDetail(HttpServletRequest request, Integer goodsId) {
        ModelAndView result = modelUtils.createModel(request, "/product/detail");
        result.addObject("goodsId", goodsId);
        return result;
    }

    /**
     * 商品类型
     */
    @RequestMapping("product/type")
    public ModelAndView productType(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/type");
    }


    /**
     * 商品类型添加
     */
    @RequestMapping("product/type/add")
    public ModelAndView productTypeAdd(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/addType");
    }

    /**
     * 商品品牌
     */
    @RequestMapping("product/brand")
    public ModelAndView productBrand(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/brand");
    }

    /**
     * 商品品牌
     */
    @RequestMapping("product/brand/add")
    public ModelAndView productBrandAdd(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/addBrand");
    }

    /**
     * 商品统计
     */
    @RequestMapping("product/stats")
    public ModelAndView productStats(HttpServletRequest request) {
        return modelUtils.createModel(request, "/product/stats");
    }

}
