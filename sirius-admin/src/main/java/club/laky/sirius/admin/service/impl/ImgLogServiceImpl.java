package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.entity.ImgLog;
import club.laky.sirius.admin.dao.ImgLogDao;
import club.laky.sirius.admin.service.ImgLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图片上传记录(ImgLog)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:40:55
 */
@Service("imgLogService")
public class ImgLogServiceImpl implements ImgLogService {
    @Resource
    private ImgLogDao imgLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ImgLog queryById(Integer id) {
        return this.imgLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ImgLog> queryAllByLimit(int offset, int limit) {
        return this.imgLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param imgLog 实例对象
     * @return 实例对象
     */
    @Override
    public ImgLog insert(ImgLog imgLog) {
        this.imgLogDao.insert(imgLog);
        return imgLog;
    }

    /**
     * 修改数据
     *
     * @param imgLog 实例对象
     * @return 实例对象
     */
    @Override
    public ImgLog update(ImgLog imgLog) {
        this.imgLogDao.update(imgLog);
        return this.queryById(imgLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.imgLogDao.deleteById(id) > 0;
    }
}