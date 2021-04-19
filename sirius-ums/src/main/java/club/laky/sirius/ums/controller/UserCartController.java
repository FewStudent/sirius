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
 * @author lakyjpan
 * @since 2021-04-18 22:10:59
 */
@RestController
@RequestMapping("/api/cart")
public class UserCartController {

    private static final Logger logger = LoggerFactory.getLogger(UserCartController.class);

    /**
     * 服务对象
     */
    @Resource
    private UserCartService userCartService;

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
     * 获取用户的购物车信息
     */
    @ResponseBody
    @RequestMapping("userCartList")
    public Object userCart(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取用户的购物车信息-------------");
            return userCartService.userCartList(jsonBody);
        } catch (Exception e) {
            logger.error("获取用户的购物车信息失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}