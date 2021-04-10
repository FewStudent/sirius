package club.laky.sirius.pms.dao;

import club.laky.sirius.pms.entity.GoodsType;
import club.laky.sirius.pms.utils.WebResult;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GoodsType)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-08 20:04:18
 */
public interface GoodsTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsType queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GoodsType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsType 实例对象
     * @return 对象列表
     */
    List<GoodsType> queryAll(GoodsType goodsType);

    /**
     * 新增数据
     *
     * @param goodsType 实例对象
     * @return 影响行数
     */
    int insert(GoodsType goodsType);

    /**
     * 修改数据
     *
     * @param goodsType 实例对象
     * @return 影响行数
     */
    int update(GoodsType goodsType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<GoodsType> queryTypeList(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("typeName") String typeName);

    int queryTypeListCount(@Param("typeName") String typeName);
}