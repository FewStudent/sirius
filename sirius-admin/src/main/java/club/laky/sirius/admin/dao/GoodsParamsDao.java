package club.laky.sirius.admin.dao;

import club.laky.sirius.admin.entity.GoodsParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsParams)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-08 13:58:18
 */
public interface GoodsParamsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsParams queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsParams> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsParams 实例对象
     * @return 对象列表
     */
    List<GoodsParams> queryAll(GoodsParams goodsParams);

    /**
     * 新增数据
     *
     * @param goodsParams 实例对象
     * @return 影响行数
     */
    int insert(GoodsParams goodsParams);

    /**
     * 修改数据
     *
     * @param goodsParams 实例对象
     * @return 影响行数
     */
    int update(GoodsParams goodsParams);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量添加
     *
     * @param paramsList
     * @return 影响行数
     */
    int insertBatch(@Param("paramsList") List<GoodsParams> paramsList);

    /**
     * 批量删除
     * @param goodsId 商品ID
     * @return d
     * */
    int deleteBatch(Integer goodsId);
}