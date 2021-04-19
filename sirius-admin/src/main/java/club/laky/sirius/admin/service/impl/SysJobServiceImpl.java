package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.constant.CacheKey;
import club.laky.sirius.admin.entity.SysJob;
import club.laky.sirius.admin.dao.SysJobDao;
import club.laky.sirius.admin.feign.FeignCacheService;
import club.laky.sirius.admin.service.SysJobService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 岗位(SysJob)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-08 19:44:03
 */
@Service("sysJobService")
public class SysJobServiceImpl implements SysJobService {
    @Resource
    private SysJobDao sysJobDao;
    @Resource
    private FeignCacheService cacheService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysJob queryById(Integer id) {
        return this.sysJobDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysJob> queryAllByLimit(int offset, int limit) {
        return this.sysJobDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysJob insert(SysJob sysJob) {
        cacheService.del(CacheKey.JOBS);
        this.sysJobDao.insert(sysJob);
        return sysJob;
    }

    /**
     * 修改数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysJob update(SysJob sysJob) {
        cacheService.del(CacheKey.JOBS);
        this.sysJobDao.update(sysJob);
        return this.queryById(sysJob.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        cacheService.del(CacheKey.JOBS);
        return this.sysJobDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryListCount(String jobName) {
        return this.sysJobDao.queryListCount(jobName);
    }

    @Override
    public List<SysJob> queryList(Integer offset, Integer limit, String jobName) {
        return this.sysJobDao.queryList(offset, limit, jobName);
    }

    @Override
    public List<SysJob> queryAll() {
        Map<String, Object> cache = (Map<String, Object>) cacheService.get(CacheKey.JOBS);
        if ("true".equals(cache.get("status"))) {
            //获取缓存成功
            String json = (String) cache.get("data");
            List<SysJob> sysJobs = JSON.parseArray(json, SysJob.class);
            return sysJobs;
        } else {
            //缓存获取失败,从数据库获取并缓存
            List<SysJob> jobList = this.sysJobDao.queryAll(null);
            String json = JSON.toJSONString(jobList);
            cacheService.set(CacheKey.JOBS, json);
            return jobList;
        }
    }
}