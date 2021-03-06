package club.laky.sirius.pms.service;


import club.laky.sirius.pms.entity.Goods;
import club.laky.sirius.pms.utils.WebResult;

import java.util.List;

/**
 * (Goods)表服务接口
 *
 * @author makejava
 * @since 2021-04-08 11:55:03
 */
public interface GoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Goods queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Goods> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    Goods insert(Goods goods);

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    Goods update(Goods goods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 商品数量
     *
     * @param brandId
     * @param goodsName
     * @param state
     * @param typeId
     * @return
     */
    Integer queryGoodsListCount(String goodsName, Integer brandId, Integer typeId, Integer state);

    /**
     * 商品列表
     *
     * @param offset
     * @param brandId
     * @param goodsName
     * @param limit
     * @param state
     * @param typeId
     * @return
     */
    List queryGoodsList(Integer offset, Integer limit, String goodsName, Integer brandId, Integer typeId, Integer state);

    /**
     * 添加商品
     *
     * @param jsonBody
     * @return
     */
    WebResult insertGoods(String jsonBody);

    /**
     * 保存商品
     *
     * @param jsonBody
     * @return
     */
    WebResult saveGoods(String jsonBody);

    WebResult onSale(Integer goodsId);

    WebResult delete(Integer goodsId);

    WebResult offShelf(Integer goodsId);

    WebResult detail(Integer goodsId);

    WebResult allOnSale(String jsonBody);

    List<Goods> getGoodsByIds(String ids);
}