package club.laky.sirius.pms.service;

import club.laky.sirius.pms.entity.GoodsBrand;

import java.util.List;

/**
 * 品牌商表(GoodsBrand)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-08 21:45:09
 */
public interface GoodsBrandService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsBrand queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsBrand> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goodsBrand 实例对象
     * @return 实例对象
     */
    GoodsBrand insert(GoodsBrand goodsBrand);

    /**
     * 修改数据
     *
     * @param goodsBrand 实例对象
     * @return 实例对象
     */
    GoodsBrand update(GoodsBrand goodsBrand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    int queryBrandListCount(String brandName);

    List<GoodsBrand> queryBrandList(Integer offset, Integer limit, String brandName);
}