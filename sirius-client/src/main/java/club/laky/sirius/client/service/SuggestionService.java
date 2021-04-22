package club.laky.sirius.client.service;

import club.laky.sirius.client.entity.Suggestion;
import java.util.List;

/**
 * (Suggestion)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-22 21:55:21
 */
public interface SuggestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Suggestion queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Suggestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param suggestion 实例对象
     * @return 实例对象
     */
    Suggestion insert(Suggestion suggestion);

    /**
     * 修改数据
     *
     * @param suggestion 实例对象
     * @return 实例对象
     */
    Suggestion update(Suggestion suggestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}