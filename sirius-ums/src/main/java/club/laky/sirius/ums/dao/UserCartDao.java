package club.laky.sirius.ums.dao;

import club.laky.sirius.ums.entity.UserCart;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户购物车表(UserCart)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:58
 */
public interface UserCartDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserCart queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserCart> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userCart 实例对象
     * @return 对象列表
     */
    List<UserCart> queryAll(UserCart userCart);

    /**
     * 新增数据
     *
     * @param userCart 实例对象
     * @return 影响行数
     */
    int insert(UserCart userCart);

    /**
     * 修改数据
     *
     * @param userCart 实例对象
     * @return 影响行数
     */
    int update(UserCart userCart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}