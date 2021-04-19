package club.laky.sirius.pms.service;

import club.laky.sirius.pms.entity.GoodsComment;
import club.laky.sirius.pms.utils.WebResult;

import java.util.List;

/**
 * (GoodsComment)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-14 00:31:01
 */
public interface GoodsCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsComment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<GoodsComment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param goodsComment 实例对象
     * @return 实例对象
     */
    GoodsComment insert(GoodsComment goodsComment);

    /**
     * 修改数据
     *
     * @param goodsComment 实例对象
     * @return 实例对象
     */
    GoodsComment update(GoodsComment goodsComment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    WebResult getCommentByGoodsId(Integer goodsId);

    WebResult commentList(Integer offset, Integer limit, String goodsName, String nickname);

    Integer commentCount(String goodsName, String nickname);

    WebResult insertComment(String jsonBody);

    WebResult reply(String jsonBody);
}