package club.laky.sirius.resources.dao;

import club.laky.sirius.resources.entity.ImgLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 图片上传记录(ImgLog)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-10 23:07:18
 */
public interface ImgLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImgLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ImgLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imgLog 实例对象
     * @return 对象列表
     */
    List<ImgLog> queryAll(ImgLog imgLog);

    /**
     * 新增数据
     *
     * @param imgLog 实例对象
     * @return 影响行数
     */
    int insert(ImgLog imgLog);

    /**
     * 修改数据
     *
     * @param imgLog 实例对象
     * @return 影响行数
     */
    int update(ImgLog imgLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int hasUser(@Param("id") Integer userId);
}