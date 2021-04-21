package club.laky.sirius.ums.dao;

import club.laky.sirius.ums.entity.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户收货地址(UserAddress)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:38
 */
public interface UserAddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserAddress queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserAddress> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userAddress 实例对象
     * @return 对象列表
     */
    List<UserAddress> queryAll(UserAddress userAddress);

    /**
     * 新增数据
     *
     * @param userAddress 实例对象
     * @return 影响行数
     */
    int insert(UserAddress userAddress);

    /**
     * 修改数据
     *
     * @param userAddress 实例对象
     * @return 影响行数
     */
    int update(UserAddress userAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<UserAddress> userAddressList(@Param("userId") Integer userId);

    Integer updateAddressStateByUserId(@Param("u_id") Integer u_id);

    List<UserAddress> adminAddressList(@Param("nickname") String nickname, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer adminAddressCount(@Param("nickname") String nickname);
}