package club.laky.sirius.oms.dao;

import club.laky.sirius.oms.entity.GoodsOrderList;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GoodsOrderList)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:56
 */
public interface GoodsOrderListDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsOrderList queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GoodsOrderList> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsOrderList 实例对象
     * @return 对象列表
     */
    List<GoodsOrderList> queryAll(GoodsOrderList goodsOrderList);

    /**
     * 新增数据
     *
     * @param goodsOrderList 实例对象
     * @return 影响行数
     */
    int insert(GoodsOrderList goodsOrderList);

    /**
     * 修改数据
     *
     * @param goodsOrderList 实例对象
     * @return 影响行数
     */
    int update(GoodsOrderList goodsOrderList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量增加
     * */
    int batchInsert(List<GoodsOrderList> goodsOrderLists);
}