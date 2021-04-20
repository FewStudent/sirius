package club.laky.sirius.ums.dao;

import club.laky.sirius.ums.entity.LoginLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 登录日志(LoginLog)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-20 00:13:49
 */
public interface LoginLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LoginLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LoginLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param loginLog 实例对象
     * @return 对象列表
     */
    List<LoginLog> queryAll(LoginLog loginLog);

    /**
     * 新增数据
     *
     * @param loginLog 实例对象
     * @return 影响行数
     */
    int insert(LoginLog loginLog);

    /**
     * 修改数据
     *
     * @param loginLog 实例对象
     * @return 影响行数
     */
    int update(LoginLog loginLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}