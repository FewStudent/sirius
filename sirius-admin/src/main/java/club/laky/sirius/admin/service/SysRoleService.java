package club.laky.sirius.admin.service;

import club.laky.sirius.admin.entity.SysRole;
import club.laky.sirius.admin.utils.WebResult;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-19 14:11:09
 */
public interface SysRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRole queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole insert(SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole update(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Integer queryCount();

    Object queryAll();

    WebResult saveRole(String jsonBody);

    WebResult savePermission(String jsonBody);

    WebResult queryByUserId(Integer user_id);

    void deleteByUserId(Integer userId);

    WebResult detail(Integer id);
}