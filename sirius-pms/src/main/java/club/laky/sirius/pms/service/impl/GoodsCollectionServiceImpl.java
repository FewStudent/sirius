package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.entity.GoodsCollection;
import club.laky.sirius.pms.dao.GoodsCollectionDao;
import club.laky.sirius.pms.service.GoodsCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GoodsCollection)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-14 00:32:15
 */
@Service("goodsCollectionService")
public class GoodsCollectionServiceImpl implements GoodsCollectionService {
    @Resource
    private GoodsCollectionDao goodsCollectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsCollection queryById(Integer id) {
        return this.goodsCollectionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsCollection> queryAllByLimit(int offset, int limit) {
        return this.goodsCollectionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsCollection 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsCollection insert(GoodsCollection goodsCollection) {
        this.goodsCollectionDao.insert(goodsCollection);
        return goodsCollection;
    }

    /**
     * 修改数据
     *
     * @param goodsCollection 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsCollection update(GoodsCollection goodsCollection) {
        this.goodsCollectionDao.update(goodsCollection);
        return this.queryById(goodsCollection.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsCollectionDao.deleteById(id) > 0;
    }
}