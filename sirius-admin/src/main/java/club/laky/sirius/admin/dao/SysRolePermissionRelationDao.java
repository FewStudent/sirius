package club.laky.sirius.admin.dao;

import club.laky.sirius.admin.entity.SysRolePermissionRelation;
import club.laky.sirius.admin.entity.SysUserRoleRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限关系表(SysRolePermissionRelation)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:48
 */
public interface SysRolePermissionRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRolePermissionRelation queryById(Integer roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRolePermissionRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 对象列表
     */
    List<SysRolePermissionRelation> queryAll(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 新增数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 影响行数
     */
    int insert(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 修改数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 影响行数
     */
    int update(SysRolePermissionRelation sysRolePermissionRelation);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

    List<SysRolePermissionRelation> queryByRoleId(@Param("roleId") Integer roleId);
}