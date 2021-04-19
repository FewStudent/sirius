package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.entity.SysUserRoleRelation;
import club.laky.sirius.admin.dao.SysUserRoleRelationDao;
import club.laky.sirius.admin.service.SysUserRoleRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关系表(SysUserRoleRelation)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:30
 */
@Service("sysUserRoleRelationService")
public class SysUserRoleRelationServiceImpl implements SysUserRoleRelationService {
    @Resource
    private SysUserRoleRelationDao sysUserRoleRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public SysUserRoleRelation queryById(Integer roleId) {
        return this.sysUserRoleRelationDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUserRoleRelation> queryAllByLimit(int offset, int limit) {
        return this.sysUserRoleRelationDao.queryAllByLimit(offset, limit);
    }


    /**
     * 修改数据
     *
     * @param sysUserRoleRelation 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRoleRelation update(SysUserRoleRelation sysUserRoleRelation) {
        this.sysUserRoleRelationDao.update(sysUserRoleRelation);
        return this.queryById(sysUserRoleRelation.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.sysUserRoleRelationDao.deleteById(roleId) > 0;
    }
}