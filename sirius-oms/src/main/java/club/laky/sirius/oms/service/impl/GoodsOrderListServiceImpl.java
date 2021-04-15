package club.laky.sirius.oms.service.impl;

import club.laky.sirius.oms.entity.GoodsOrderList;
import club.laky.sirius.oms.dao.GoodsOrderListDao;
import club.laky.sirius.oms.service.GoodsOrderListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GoodsOrderList)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:56
 */
@Service("goodsOrderListService")
public class GoodsOrderListServiceImpl implements GoodsOrderListService {
    @Resource
    private GoodsOrderListDao goodsOrderListDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsOrderList queryById(Integer id) {
        return this.goodsOrderListDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsOrderList> queryAllByLimit(int offset, int limit) {
        return this.goodsOrderListDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsOrderList 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsOrderList insert(GoodsOrderList goodsOrderList) {
        this.goodsOrderListDao.insert(goodsOrderList);
        return goodsOrderList;
    }

    /**
     * 修改数据
     *
     * @param goodsOrderList 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsOrderList update(GoodsOrderList goodsOrderList) {
        this.goodsOrderListDao.update(goodsOrderList);
        return this.queryById(goodsOrderList.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsOrderListDao.deleteById(id) > 0;
    }
}