package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.dao.SysPermissionDao;
import club.laky.sirius.admin.dao.SysRolePermissionRelationDao;
import club.laky.sirius.admin.dao.SysUserRoleRelationDao;
import club.laky.sirius.admin.entity.SysRole;
import club.laky.sirius.admin.dao.SysRoleDao;
import club.laky.sirius.admin.entity.SysUserRoleRelation;
import club.laky.sirius.admin.service.SysRoleService;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:11:09
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;
    @Resource
    private SysPermissionDao permissionDao;
    @Resource
    private SysUserRoleRelationDao sysUserRoleRelationDao;
    @Resource
    private SysRolePermissionRelationDao permissionRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Integer id) {
        return this.sysRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRole> queryAllByLimit(int offset, int limit) {
        return this.sysRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleDao.insert(sysRole);
        return sysRole;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleDao.update(sysRole);
        return this.queryById(sysRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        int result = this.sysRoleDao.deleteById(id);
        if (result == 1) {
            int count = permissionRelationDao.deleteByRoleId(id);
            return true;
        }
        return false;
    }

    @Override
    public Integer queryCount() {
        return this.sysRoleDao.queryCount();

    }

    @Override
    public Object queryAll() {
        return this.sysRoleDao.queryAll(null);
    }

    @Override
    public WebResult saveRole(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer user_id = params.getInteger("user_id");
        String ids = params.getString("ids");
        sysRoleDao.deleteByUserId(user_id);
        List<String> idList = Arrays.asList(ids.split(","));
        Integer result = sysRoleDao.batchInsertRelation(idList, user_id);
        if (result == 0) {
            return WebResult.error("保存失败");
        }

        return WebResult.success("保存成功!");
    }

    @Override
    public WebResult savePermission(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer role_id = params.getInteger("role_id");
        String ids = params.getString("ids");
        permissionDao.deleteByRoleId(role_id);
        List<String> idList = Arrays.asList(ids.split(","));
        Integer result = sysRoleDao.batchInsertPermissionRelation(idList, role_id);
        if (result == 0) {
            return WebResult.error("保存失败");
        }

        return WebResult.success("保存成功!");
    }

    @Override
    public WebResult queryByUserId(Integer user_id) {
        List<SysUserRoleRelation> roleRelations = sysUserRoleRelationDao.queryByUserId(user_id);
        if (roleRelations == null) {
            return WebResult.error("获取失败");
        }
        return WebResult.success(roleRelations);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        sysRoleDao.deleteByUserId(userId);
    }

    @Override
    public WebResult detail(Integer id) {
        return WebResult.success(this.sysRoleDao.queryOne(id));
    }
}