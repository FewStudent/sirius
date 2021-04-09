package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.entity.GoodsBrand;
import club.laky.sirius.pms.dao.GoodsBrandDao;
import club.laky.sirius.pms.service.GoodsBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌商表(GoodsBrand)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-08 21:45:09
 */
@Service("goodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
    @Resource
    private GoodsBrandDao goodsBrandDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsBrand queryById(Integer id) {
        return this.goodsBrandDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsBrand> queryAllByLimit(int offset, int limit) {
        return this.goodsBrandDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsBrand 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsBrand insert(GoodsBrand goodsBrand) {
        this.goodsBrandDao.insert(goodsBrand);
        return goodsBrand;
    }

    /**
     * 修改数据
     *
     * @param goodsBrand 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsBrand update(GoodsBrand goodsBrand) {
        this.goodsBrandDao.update(goodsBrand);
        return this.queryById(goodsBrand.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsBrandDao.deleteById(id) > 0;
    }

    @Override
    public int queryBrandListCount(String brandName) {
        return this.goodsBrandDao.queryBrandListCount(brandName);
    }

    @Override
    public List<GoodsBrand>  queryBrandList(Integer offset, Integer limit, String brandName) {
        return this.goodsBrandDao.queryBrandList(offset,limit,brandName);
    }
}