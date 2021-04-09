package club.laky.sirius.pms.service;

import club.laky.sirius.pms.entity.GoodsType;
import java.util.List;

/**
 * (GoodsType)表服务接口
 *
 * @author lakyjapn
 * @since 2021-04-08 20:04:18
 */
public interface GoodsTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsType queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GoodsType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goodsType 实例对象
     * @return 实例对象
     */
    GoodsType insert(GoodsType goodsType);

    /**
     * 修改数据
     *
     * @param goodsType 实例对象
     * @return 实例对象
     */
    GoodsType update(GoodsType goodsType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}