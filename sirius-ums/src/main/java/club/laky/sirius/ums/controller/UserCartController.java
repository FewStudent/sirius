package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.entity.UserCart;
import club.laky.sirius.ums.service.UserCartService;
import club.laky.sirius.ums.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户购物车表(UserCart)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:59
 */
@RestController
@RequestMapping("/api/cart")
public class UserCartController {
    /**
     * 服务对象
     */
    @Resource
    private UserCartService userCartService;

    private static final Logger logger = LoggerFactory.getLogger(UserCartController.class);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserCart selectOne(Integer id) {
        return this.userCartService.queryById(id);
    }

    /**
     * 后台管理列表
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/cartList")
    public Object cartList(@RequestParam String jsonBody) {
        try {
            logger.info("-------------后台管理列表-------------");
            return userCartService.cartList(jsonBody);
        } catch (Exception e) {
            logger.info("后台管理列表失败:{}", e.getMessage());
            return WebResult.error("后台管理列表失败");
        }
    }

    /**
     * 后台管理数量
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/cartCount")
    public Object cartCount(@RequestParam String jsonBody) {
        try {
            logger.info("-------------后台管理数量-------------");
            return userCartService.cartCount(jsonBody);
        } catch (Exception e) {
            logger.info("后台管理数量失败:{}", e.getMessage());
            return WebResult.error("后台管理数量失败");
        }
    }

    /**
     * 获取用户购物车信息
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/userCartList")
    public Object userCartList(@RequestParam Integer userId) {
        try {
            logger.info("-------------获取用户购物车信息-------------");
            return userCartService.userCartList(userId);
        } catch (Exception e) {
            logger.info("获取用户购物车信息失败:{}", e.getMessage());
            return WebResult.error("获取用户购物车信息失败");
        }
    }

    /**
     * 删减购物车商品
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/clearCart")
    public Object clearCart(@RequestParam String cartIds) {
        try {
            logger.info("-------------删减购物车商品-------------");
            return userCartService.clearCart(cartIds);
        } catch (Exception e) {
            logger.info("删减购物车商品失败:{}", e.getMessage());
            return WebResult.error("删减购物车商品失败");
        }
    }

    /**
     * 删除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/deleteCart")
    public Object deleteCart(@RequestParam Integer id) {
        try {
            logger.info("-------------删除-------------");
            return userCartService.deleteCart(id);
        } catch (Exception e) {
            logger.info("删除失败:{}", e.getMessage());
            return WebResult.error("删除失败");
        }
    }

    /**
     * 添加
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/insertCart")
    public Object insertCart(@RequestBody String jsonBody) {
        try {
            logger.info("-------------添加-------------");
            return userCartService.insertCart(jsonBody);
        } catch (Exception e) {
            logger.info("添加失败:{}", e.getMessage());
            return WebResult.error("添加失败");
        }
    }

}