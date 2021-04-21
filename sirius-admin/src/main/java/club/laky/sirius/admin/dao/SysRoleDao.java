package club.laky.sirius.admin.dao;

import club.laky.sirius.admin.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:11:09
 */
public interface SysRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRole queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRole 实例对象
     * @return 对象列表
     */
    List<SysRole> queryAll(SysRole sysRole);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int insert(SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int update(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select count(*) from sys_role")
    Integer queryCount();

    @Delete("delete from sys_user_role_relation where user_id = #{user_id}")
    Integer deleteByUserId(@Param("user_id") Integer user_id);

    Integer batchInsertRelation(@Param("idList") List<String> idList, @Param("user_id") Integer user_id);

    Integer batchInsertPermissionRelation(@Param("idList") List<String> idList, @Param("role_id") Integer role_id);

    SysRole queryOne(Integer id);
}