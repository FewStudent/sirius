package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.constant.CacheKey;
import club.laky.sirius.admin.entity.SysDepartment;
import club.laky.sirius.admin.dao.SysDepartmentDao;
import club.laky.sirius.admin.feign.FeignCacheService;
import club.laky.sirius.admin.service.SysDepartmentService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 部门(SysDepartment)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-08 19:43:48
 */
@Service("sysDepartmentService")
public class SysDepartmentServiceImpl implements SysDepartmentService {
    @Resource
    private SysDepartmentDao sysDepartmentDao;
    @Resource
    private FeignCacheService cacheService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysDepartment queryById(Integer id) {
        return this.sysDepartmentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysDepartment> queryAllByLimit(int offset, int limit) {
        return this.sysDepartmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public SysDepartment insert(SysDepartment sysDepartment) {
        cacheService.del(CacheKey.DEPARTMENTS);
        this.sysDepartmentDao.insert(sysDepartment);
        return sysDepartment;
    }

    /**
     * 修改数据
     *
     * @param sysDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public SysDepartment update(SysDepartment sysDepartment) {
        cacheService.del(CacheKey.DEPARTMENTS);
        this.sysDepartmentDao.update(sysDepartment);
        return this.queryById(sysDepartment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        cacheService.del(CacheKey.DEPARTMENTS);
        return this.sysDepartmentDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryListCount(String departmentName) {
        return this.sysDepartmentDao.queryListCount(departmentName);
    }

    @Override
    public List<SysDepartment> queryList(Integer offset, Integer limit, String departmentName) {
        return this.sysDepartmentDao.queryList(offset, limit, departmentName);
    }

    @Override
    public List<SysDepartment> queryAll() {
        Map<String, Object> cache = (Map<String, Object>) cacheService.get(CacheKey.DEPARTMENTS);
        if ("true".equals(cache.get("status"))) {
            //获取缓存成功
            String json = (String) cache.get("data");
            List<SysDepartment> sysDepartments = JSON.parseArray(json, SysDepartment.class);
            return sysDepartments;
        } else {
            //缓存获取失败,从数据库获取并缓存
            List<SysDepartment> departmentList = this.sysDepartmentDao.queryAll(null);
            String json = JSON.toJSONString(departmentList);
            cacheService.set(CacheKey.DEPARTMENTS, json);
            return departmentList;
        }
    }

//    public static void main(String[] args) {
//        List<SysDepartment> departmentList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            SysDepartment sysDepartment = new SysDepartment();
//            sysDepartment.setDepDescription("描述");
//            sysDepartment.setDepLevel(1);
//            sysDepartment.setId(i);
//            sysDepartment.setDepName("好");
//            departmentList.add(sysDepartment);
//        }
//        String json = JSON.toJSONString(departmentList);
//        System.out.println(json);
//        List<SysDepartment> sysDepartments = JSON.parseArray(json, SysDepartment.class);
//        for (SysDepartment sysDepartment : sysDepartments) {
//            System.out.println(sysDepartment);
//        }
//    }
}