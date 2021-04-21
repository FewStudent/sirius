package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.entity.Suggestion;
import club.laky.sirius.admin.dao.SuggestionDao;
import club.laky.sirius.admin.service.SuggestionService;
import club.laky.sirius.admin.utils.WebResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Suggestion)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-22 00:06:38
 */
@Service("suggestionService")
public class SuggestionServiceImpl implements SuggestionService {
    @Resource
    private SuggestionDao suggestionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Suggestion queryById(Integer id) {
        return this.suggestionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Suggestion> queryAllByLimit(int offset, int limit) {
        return this.suggestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param suggestion 实例对象
     * @return 实例对象
     */
    @Override
    public Suggestion insert(Suggestion suggestion) {
        this.suggestionDao.insert(suggestion);
        return suggestion;
    }

    /**
     * 修改数据
     *
     * @param suggestion 实例对象
     * @return 实例对象
     */
    @Override
    public Suggestion update(Suggestion suggestion) {
        this.suggestionDao.update(suggestion);
        return this.queryById(suggestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.suggestionDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryListCount() {
        return this.suggestionDao.queryListCount();
    }

    @Override
    public List<Suggestion> queryList(Integer offset, Integer limit) {
        return this.suggestionDao.queryList(offset, limit);
    }

    @Override
    public WebResult updateById(Integer id) {
        return WebResult.success(this.suggestionDao.updateById(id));
    }
}