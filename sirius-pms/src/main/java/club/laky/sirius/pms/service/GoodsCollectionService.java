package club.laky.sirius.pms.service;

import club.laky.sirius.pms.entity.GoodsCollection;
import java.util.List;

/**
 * (GoodsCollection)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-14 00:32:15
 */
public interface GoodsCollectionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsCollection queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GoodsCollection> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goodsCollection 实例对象
     * @return 实例对象
     */
    GoodsCollection insert(GoodsCollection goodsCollection);

    /**
     * 修改数据
     *
     * @param goodsCollection 实例对象
     * @return 实例对象
     */
    GoodsCollection update(GoodsCollection goodsCollection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}