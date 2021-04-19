package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.entity.UserCart;
import club.laky.sirius.ums.service.UserCartService;
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

}