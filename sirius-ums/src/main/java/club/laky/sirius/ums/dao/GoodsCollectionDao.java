package club.laky.sirius.ums.dao;

import club.laky.sirius.ums.entity.GoodsCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsCollection)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:33
 */
public interface GoodsCollectionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsCollection queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsCollection> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsCollection 实例对象
     * @return 对象列表
     */
    List<GoodsCollection> queryAll(GoodsCollection goodsCollection);

    /**
     * 新增数据
     *
     * @param goodsCollection 实例对象
     * @return 影响行数
     */
    int insert(GoodsCollection goodsCollection);

    /**
     * 修改数据
     *
     * @param goodsCollection 实例对象
     * @return 影响行数
     */
    int update(GoodsCollection goodsCollection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<GoodsCollection> collections(@Param("userId") Integer userId);

    List<GoodsCollection> adminCollectionList(@Param("nickname") String nickname, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer adminCollectionCount(@Param("nickname") String nickname);

    Integer hasCollect(@Param("userId") Integer userId,@Param("goodsId") Integer goodsId);

    Integer deleteByTwoId(@Param("userId") Integer userId,@Param("goodsId") Integer goodsId);
}