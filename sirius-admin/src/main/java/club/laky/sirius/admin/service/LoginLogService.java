package club.laky.sirius.admin.service;

import club.laky.sirius.admin.entity.LoginLog;
import java.util.List;

/**
 * 登录日志(LoginLog)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-19 14:41:13
 */
public interface LoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LoginLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LoginLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    LoginLog insert(LoginLog loginLog);

    /**
     * 修改数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    LoginLog update(LoginLog loginLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}