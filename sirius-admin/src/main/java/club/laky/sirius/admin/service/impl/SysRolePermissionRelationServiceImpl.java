package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.entity.SysRolePermissionRelation;
import club.laky.sirius.admin.dao.SysRolePermissionRelationDao;
import club.laky.sirius.admin.service.SysRolePermissionRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限关系表(SysRolePermissionRelation)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:48
 */
@Service("sysRolePermissionRelationService")
public class SysRolePermissionRelationServiceImpl implements SysRolePermissionRelationService {
    @Resource
    private SysRolePermissionRelationDao sysRolePermissionRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public SysRolePermissionRelation queryById(Integer roleId) {
        return this.sysRolePermissionRelationDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRolePermissionRelation> queryAllByLimit(int offset, int limit) {
        return this.sysRolePermissionRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolePermissionRelation insert(SysRolePermissionRelation sysRolePermissionRelation) {
        this.sysRolePermissionRelationDao.insert(sysRolePermissionRelation);
        return sysRolePermissionRelation;
    }

    /**
     * 修改数据
     *
     * @param sysRolePermissionRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysRolePermissionRelation update(SysRolePermissionRelation sysRolePermissionRelation) {
        this.sysRolePermissionRelationDao.update(sysRolePermissionRelation);
        return this.queryById(sysRolePermissionRelation.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.sysRolePermissionRelationDao.deleteById(roleId) > 0;
    }
}