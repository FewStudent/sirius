package club.laky.sirius.ums.service;

import club.laky.sirius.ums.entity.UserCart;
import java.util.List;

/**
 * 用户购物车表(UserCart)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:58
 */
public interface UserCartService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserCart queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserCart> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userCart 实例对象
     * @return 实例对象
     */
    UserCart insert(UserCart userCart);

    /**
     * 修改数据
     *
     * @param userCart 实例对象
     * @return 实例对象
     */
    UserCart update(UserCart userCart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}