package club.laky.sirius.admin.dao;

import club.laky.sirius.admin.entity.Suggestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Suggestion)表数据库访问层
 *
 * @author lakyjpan
 * @since 2021-04-22 00:06:38
 */
public interface SuggestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Suggestion queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Suggestion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param suggestion 实例对象
     * @return 对象列表
     */
    List<Suggestion> queryAll(Suggestion suggestion);

    /**
     * 新增数据
     *
     * @param suggestion 实例对象
     * @return 影响行数
     */
    int insert(Suggestion suggestion);

    /**
     * 修改数据
     *
     * @param suggestion 实例对象
     * @return 影响行数
     */
    int update(Suggestion suggestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Integer queryListCount();

    List<Suggestion> queryList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer updateById(@Param("id") Integer id);
}