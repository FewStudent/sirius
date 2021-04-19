package club.laky.sirius.ums.dao;

import club.laky.sirius.ums.entity.SysUserRoleRelation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户角色关系表(SysUserRoleRelation)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-19 10:21:55
 */
public interface SysUserRoleRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysUserRoleRelation queryById(Integer roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUserRoleRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRoleRelation 实例对象
     * @return 对象列表
     */
    List<SysUserRoleRelation> queryAll(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 新增数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 影响行数
     */
    int insert(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 修改数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 影响行数
     */
    int update(SysUserRoleRelation sysUserRoleRelation);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

}