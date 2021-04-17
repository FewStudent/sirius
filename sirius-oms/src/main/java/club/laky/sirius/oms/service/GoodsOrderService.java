package club.laky.sirius.oms.service;

import club.laky.sirius.oms.entity.GoodsOrder;
import club.laky.sirius.oms.utils.WebResult;

import java.util.List;

/**
 * (GoodsOrder)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:37
 */
public interface GoodsOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GoodsOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goodsOrder 实例对象
     * @return 实例对象
     */
    GoodsOrder insert(GoodsOrder goodsOrder);

    /**
     * 修改数据
     *
     * @param goodsOrder 实例对象
     * @return 实例对象
     */
    GoodsOrder update(GoodsOrder goodsOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    /**
     * 保存订单
     * */
    WebResult saveOrder(String jsonBody);

    WebResult orderDetail(String orderNum);

    WebResult getMyOrders(String jsonBody);

    WebResult getAdminOrders(String jsonBody);

    WebResult checkSendOrder(Integer orderId);

    WebResult cancelOrder(Integer orderId);

    WebResult checkTakeOrder(Integer orderId);

    WebResult closeOrder(Integer orderId);
}