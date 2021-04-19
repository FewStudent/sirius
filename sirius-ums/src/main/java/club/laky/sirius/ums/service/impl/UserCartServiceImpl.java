package club.laky.sirius.ums.service.impl;

import club.laky.sirius.ums.entity.UserCart;
import club.laky.sirius.ums.dao.UserCartDao;
import club.laky.sirius.ums.service.UserCartService;
import club.laky.sirius.ums.utils.WebResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户购物车表(UserCart)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:59
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
    public WebResult userCartList(String jsonBody) {
        return null;
    }
}