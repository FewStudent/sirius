package club.laky.sirius.pms.dao;

import club.laky.sirius.pms.entity.GoodsBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌商表(GoodsBrand)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-08 21:45:09
 */
public interface GoodsBrandDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsBrand queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsBrand> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsBrand 实例对象
     * @return 对象列表
     */
    List<GoodsBrand> queryAll(GoodsBrand goodsBrand);

    /**
     * 新增数据
     *
     * @param goodsBrand 实例对象
     * @return 影响行数
     */
    int insert(GoodsBrand goodsBrand);

    /**
     * 修改数据
     *
     * @param goodsBrand 实例对象
     * @return 影响行数
     */
    int update(GoodsBrand goodsBrand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<GoodsBrand> queryBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("brandName") String brandName);

    int queryBrandListCount(@Param("brandName") String brandName);
}