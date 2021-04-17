package club.laky.sirius.oms.dao;

import club.laky.sirius.oms.entity.GoodsOrder;
import club.laky.sirius.oms.entity.GoodsOrderList;
import net.sf.jsqlparser.statement.select.Offset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsOrder)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:37
 */
public interface GoodsOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsOrder queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsOrder 实例对象
     * @return 对象列表
     */
    List<GoodsOrder> queryAll(GoodsOrder goodsOrder);

    /**
     * 新增数据
     *
     * @param goodsOrder 实例对象
     * @return 影响行数
     */
    int insert(GoodsOrder goodsOrder);

    /**
     * 修改数据
     *
     * @param goodsOrder 实例对象
     * @return 影响行数
     */
    int update(GoodsOrder goodsOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int queryGoodsCount(@Param("ids") List<String> ids);

    GoodsOrder queryOrderDetail(@Param("orderNum") String orderNum);

    /**
     * 获取我的订单
     * */
    List<GoodsOrder> queryMyOrder(@Param("userId") Integer userId, @Param("state") Integer state,
                                  @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer queryMyOrderCount(@Param("userId") Integer userId, @Param("state") Integer state);

    List<GoodsOrder> queryAdminOrders(@Param("no") String orderNum,@Param("state") Integer state,@Param("nickname") String nickname,
                                      @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer queryAdminOrdersCount(@Param("no") String orderNum,@Param("state") Integer state,
                                  @Param("nickname") String nickname);

}