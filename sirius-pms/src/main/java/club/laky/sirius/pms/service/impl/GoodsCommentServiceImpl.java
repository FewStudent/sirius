package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.entity.GoodsComment;
import club.laky.sirius.pms.dao.GoodsCommentDao;
import club.laky.sirius.pms.service.GoodsCommentService;
import club.laky.sirius.pms.utils.WebResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GoodsComment)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-14 00:31:01
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl implements GoodsCommentService {
    @Resource
    private GoodsCommentDao goodsCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsComment queryById(Integer id) {
        return this.goodsCommentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsComment> queryAllByLimit(int offset, int limit) {
        return this.goodsCommentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsComment 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsComment insert(GoodsComment goodsComment) {
        this.goodsCommentDao.insert(goodsComment);
        return goodsComment;
    }

    /**
     * 修改数据
     *
     * @param goodsComment 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsComment update(GoodsComment goodsComment) {
        this.goodsCommentDao.update(goodsComment);
        return this.queryById(goodsComment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsCommentDao.deleteById(id) > 0;
    }

    @Override
    public WebResult getCommentByGoodsId(Integer goodsId) {
        return WebResult.success(this.goodsCommentDao.queryByGoodsId(goodsId));
    }
}