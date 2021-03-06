package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.entity.LoginLog;
import club.laky.sirius.admin.dao.LoginLogDao;
import club.laky.sirius.admin.service.LoginLogService;
import club.laky.sirius.admin.utils.WebResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志(LoginLog)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:41:13
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogDao loginLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LoginLog queryById(Integer id) {
        return this.loginLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<LoginLog> queryAllByLimit(int offset, int limit) {
        return this.loginLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    @Override
    public LoginLog insert(LoginLog loginLog) {
        this.loginLogDao.insert(loginLog);
        return loginLog;
    }

    /**
     * 修改数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    @Override
    public LoginLog update(LoginLog loginLog) {
        this.loginLogDao.update(loginLog);
        return this.queryById(loginLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public WebResult deleteById(Integer id) {
        if (this.loginLogDao.deleteById(id) > 0) {
            return WebResult.success("删除成功");
        }
        return WebResult.error("删除失败");
    }

    @Override
    public WebResult deleteAll() {
        if (this.loginLogDao.delteAll() > 0) {
            return WebResult.success("删除成功");
        }
        return WebResult.error("删除失败");
    }

    @Override
    public Integer queryListCount() {
        return this.loginLogDao.queryListCount();
    }

    @Override
    public List<LoginLog> queryList(Integer offset, Integer limit) {
        return this.loginLogDao.queryList(offset, limit);
    }
}