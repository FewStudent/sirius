package club.laky.sirius.admin.service;

import club.laky.sirius.admin.entity.SysUserRoleRelation;
import java.util.List;

/**
 * 用户角色关系表(SysUserRoleRelation)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:30
 */
public interface SysUserRoleRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysUserRoleRelation queryById(Integer roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUserRoleRelation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 实例对象
     */
    SysUserRoleRelation insert(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 修改数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 实例对象
     */
    SysUserRoleRelation update(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

}