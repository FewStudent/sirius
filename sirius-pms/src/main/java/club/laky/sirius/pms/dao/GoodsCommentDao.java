package club.laky.sirius.pms.dao;

import club.laky.sirius.pms.entity.GoodsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsComment)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-14 00:31:01
 */
public interface GoodsCommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsComment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsComment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsComment 实例对象
     * @return 对象列表
     */
    List<GoodsComment> queryAll(GoodsComment goodsComment);

    /**
     * 新增数据
     *
     * @param goodsComment 实例对象
     * @return 影响行数
     */
    int insert(GoodsComment goodsComment);

    /**
     * 修改数据
     *
     * @param goodsComment 实例对象
     * @return 影响行数
     */
    int update(GoodsComment goodsComment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<GoodsComment> queryByGoodsId(@Param("goodsId") Integer goodsId);

    List<GoodsComment> commentList(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("goodsName") String goodsName, @Param("nickname") String nickname);

    Integer commentCount(@Param("goodsName") String goodsName, @Param("nickname") String nickname);
}