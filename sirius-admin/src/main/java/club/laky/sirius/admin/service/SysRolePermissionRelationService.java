package club.laky.sirius.admin.service;

import club.laky.sirius.admin.entity.SysRolePermissionRelation;
import java.util.List;

/**
 * 角色权限关系表(SysRolePermissionRelation)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:48
 */
public interface SysRolePermissionRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRolePermissionRelation queryById(Integer roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRolePermissionRelation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 实例对象
     */
    SysRolePermissionRelation insert(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 修改数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 实例对象
     */
    SysRolePermissionRelation update(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

}