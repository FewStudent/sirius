package club.laky.sirius.admin.service;

import club.laky.sirius.admin.entity.SysJob;

import java.util.List;

/**
 * 岗位(SysJob)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-08 19:44:03
 */
public interface SysJobService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysJob queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysJob> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    SysJob insert(SysJob sysJob);

    /**
     * 修改数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    SysJob update(SysJob sysJob);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Integer queryListCount(String jobName);

    List<SysJob> queryList(Integer offset, Integer limit, String jobName);

    List<SysJob> queryAll();
}