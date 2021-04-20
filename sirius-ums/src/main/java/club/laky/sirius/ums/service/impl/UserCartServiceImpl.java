package club.laky.sirius.ums.service.impl;

import club.laky.sirius.ums.entity.UserCart;
import club.laky.sirius.ums.dao.UserCartDao;
import club.laky.sirius.ums.service.UserCartService;
import club.laky.sirius.ums.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 用户购物车表(UserCart)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:59
 */
@Service("userCartService")
public class UserCartServiceImpl implements UserCartService {
    @Resource
    private UserCartDao userCartDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserCart queryById(Integer id) {
        return this.userCartDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserCart> queryAllByLimit(int offset, int limit) {
        return this.userCartDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userCart 实例对象
     * @return 实例对象
     */
    @Override
    public UserCart insert(UserCart userCart) {
        this.userCartDao.insert(userCart);
        return userCart;
    }

    /**
     * 修改数据
     *
     * @param userCart 实例对象
     * @return 实例对象
     */
    @Override
    public UserCart update(UserCart userCart) {
        this.userCartDao.update(userCart);
        return this.queryById(userCart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userCartDao.deleteById(id) > 0;
    }

    @Override
    public WebResult cartList(String jsonBody) {
        JSONObject object = JSONObject.parseObject(jsonBody);
        String nickname = object.getString("nickname");
        Integer page = object.getInteger("page");
        Integer limit = object.getInteger("limit");

        List<UserCart> cartList = this.userCartDao.cartList(nickname, (page - 1) * limit, limit);
        if (cartList != null) {
            return WebResult.success(cartList);
        }
        return WebResult.error("获取失败");
    }

    @Override
    public Integer cartCount(String jsonBody) {
        JSONObject object = JSONObject.parseObject(jsonBody);
        String nickname = object.getString("nickname");
        return this.userCartDao.cartCount(nickname);
    }

    @Override
    public WebResult userCartList(Integer userId) {
        List<UserCart> userCartList = this.userCartDao.userCartList(userId);
        if (userCartList == null) {
            return WebResult.error("获取失败");
        }
        return WebResult.success(userCartList);
    }

    @Override
    public WebResult clearCart(String goodsIdList) {
        List<String> ids = Arrays.asList(goodsIdList.split(","));
        this.userCartDao.clearCart(ids);
        return WebResult.success("清算结束");
    }

    @Override
    public WebResult deleteCart(Integer id) {
        int result = this.userCartDao.deleteById(id);
        if (result == 0) {
            return WebResult.error("删除失败");
        }
        return WebResult.success("删除成功！");
    }

    @Override
    public WebResult insertCart(String jsonBody) {
        JSONObject object = JSONObject.parseObject(jsonBody);
        Integer goodsId = object.getInteger("goodsId");
        Integer count = object.getInteger("count");
        Integer uId = object.getInteger("uId");

        UserCart userCart = new UserCart();
        userCart.setCount(count);
        userCart.setGoodsId(goodsId);
        userCart.setUId(uId);
        if (userCartDao.insert(userCart) == 0) {
            WebResult.error("添加失败");
        }
        return WebResult.success("添加成功！");
    }
}