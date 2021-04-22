package club.laky.sirius.ums.service;

import club.laky.sirius.ums.entity.GoodsCollection;
import club.laky.sirius.ums.utils.WebResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsCollection)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:33
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
     * @param limit  查询条数
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

    WebResult collections(Integer userId);

    WebResult deleteCollection(Integer id);

    WebResult insertCollection(String jsonBody);

    WebResult adminCollectionList(String jsonBody);

    Integer adminCollectionCount(String jsonBody);

    Integer hasCollect(Integer goodsId, Integer userId);

    Integer deleteByTwoId(Integer userId, Integer goodsId);
}