package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.dao.SysRolePermissionRelationDao;
import club.laky.sirius.admin.entity.SysPermission;
import club.laky.sirius.admin.dao.SysPermissionDao;
import club.laky.sirius.admin.entity.SysRolePermissionRelation;
import club.laky.sirius.admin.entity.SysUserRoleRelation;
import club.laky.sirius.admin.service.SysPermissionService;
import club.laky.sirius.admin.utils.WebResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:11:52
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionDao sysPermissionDao;
    @Resource
    private SysRolePermissionRelationDao relationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPermission queryById(Integer id) {
        return this.sysPermissionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysPermission> queryAllByLimit(int offset, int limit) {
        return this.sysPermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysPermission insert(SysPermission sysPermission) {
        this.sysPermissionDao.insert(sysPermission);
        return sysPermission;
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public SysPermission update(SysPermission sysPermission) {
        this.sysPermissionDao.update(sysPermission);
        return this.queryById(sysPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysPermissionDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryCount() {
        return this.sysPermissionDao.queryCount();
    }

    @Override
    public Object queryAll() {
        return this.sysPermissionDao.queryAll(null);
    }

    @Override
    public WebResult queryByRoleId(Integer roleId) {
        List<SysRolePermissionRelation> roleRelations = relationDao.queryByRoleId(roleId);
        if (roleRelations == null) {
            return WebResult.error("获取失败");
        }
        return WebResult.success(roleRelations);
    }
}