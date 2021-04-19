package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.entity.GoodsComment;
import club.laky.sirius.pms.dao.GoodsCommentDao;
import club.laky.sirius.pms.service.GoodsCommentService;
import club.laky.sirius.pms.utils.WebResult;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
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

    @Override
    public WebResult commentList(Integer offset, Integer limit, String goodsName, String nickname) {
        return WebResult.success(this.goodsCommentDao.commentList(offset, limit, goodsName, nickname));
    }

    @Override
    public Integer commentCount(String goodsName, String nickname) {
        return this.goodsCommentDao.commentCount(goodsName, nickname);
    }

    @Override
    public WebResult insertComment(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer goodsId = params.getInteger("goodsId");
        Integer u_id = params.getInteger("u_id");
        String content = params.getString("content");
        GoodsComment goodsComment = new GoodsComment();
        goodsComment.setUId(u_id);
        goodsComment.setGoodsId(goodsId);
        goodsComment.setContent(content);
        goodsComment.setCreateTime(DateUtil.now());
        if (goodsCommentDao.insert(goodsComment) == 1) {
            return WebResult.success("评论成功!");
        }
        return WebResult.error("评论失败!");
    }

    @Override
    public WebResult reply(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer id = params.getInteger("id");
        Integer reply_by = params.getInteger("reply_by");
        String content = params.getString("content");
        GoodsComment goodsComment = new GoodsComment();
        goodsComment.setReplyBy(reply_by);
        goodsComment.setId(id);
        goodsComment.setReply(content);
        goodsComment.setReplyTime(DateUtil.now());
        if (goodsCommentDao.update(goodsComment) == 1) {
            return WebResult.success("评论回复成功!");
        }
        return WebResult.error("评论回复失败!");
    }
}