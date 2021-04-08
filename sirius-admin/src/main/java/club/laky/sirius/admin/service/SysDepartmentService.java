package club.laky.sirius.admin.service;

import club.laky.sirius.admin.entity.SysDepartment;

import java.util.List;

/**
 * 部门(SysDepartment)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-08 19:43:48
 */
public interface SysDepartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDepartment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysDepartment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysDepartment 实例对象
     * @return 实例对象
     */
    SysDepartment insert(SysDepartment sysDepartment);

    /**
     * 修改数据
     *
     * @param sysDepartment 实例对象
     * @return 实例对象
     */
    SysDepartment update(SysDepartment sysDepartment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Integer queryListCount(String departmentName);

    List<SysDepartment> queryList(Integer offset, Integer limit, String departmentName);

    List<SysDepartment> queryAll();
}