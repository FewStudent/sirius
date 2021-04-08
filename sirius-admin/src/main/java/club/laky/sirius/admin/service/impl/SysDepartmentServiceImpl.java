package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.entity.SysDepartment;
import club.laky.sirius.admin.dao.SysDepartmentDao;
import club.laky.sirius.admin.service.SysDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}