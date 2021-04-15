package club.laky.sirius.oms.service;

import club.laky.sirius.oms.entity.GoodsOrderList;
import java.util.List;

/**
 * (GoodsOrderList)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:56
 */
public interface GoodsOrderListService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsOrderList queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GoodsOrderList> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goodsOrderList 实例对象
     * @return 实例对象
     */
    GoodsOrderList insert(GoodsOrderList goodsOrderList);

    /**
     * 修改数据
     *
     * @param goodsOrderList 实例对象
     * @return 实例对象
     */
    GoodsOrderList update(GoodsOrderList goodsOrderList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}