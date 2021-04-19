package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsCommentService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GoodsComment)表控制层
 *
 * @author lakyjpan
 * @since 2021-04-14 00:31:02
 */
@RestController
@RequestMapping("/api/comment")
public class GoodsCommentController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsCommentController.class);

    /**
     * 服务对象
     */
    @Resource
    private GoodsCommentService goodsCommentService;

    /**
     * 获取商品的评价
     */
    @ResponseBody
    @RequestMapping("/getCommentByGoodsId")
    public Object getCommentByGoodsId(@RequestParam Integer goodsId) {
        try {
            logger.info("-------------获取商品的评价-------------");
            return goodsCommentService.getCommentByGoodsId(goodsId);
        } catch (Exception e) {
            logger.error("获取商品的评价失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品评论
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/insertComment")
    public Object insertComment(@RequestBody String jsonBody) {
        try {
            logger.info("-------------商品评论-------------");
            return goodsCommentService.insertComment(jsonBody);
        } catch (Exception e) {
            logger.info("商品评论失败:{}", e.getMessage());
            return WebResult.error("商品评论失败");
        }
    }

    /**
     * 评论回复
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/reply")
    public Object reply(@RequestBody String jsonBody) {
        try {
            logger.info("-------------评论回复-------------");
            return goodsCommentService.reply(jsonBody);
        } catch (Exception e) {
            logger.info("评论回复失败:{}", e.getMessage());
            return WebResult.error("评论回复失败");
        }
    }

    /**
     * 评论删除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/deleteComment")
    public Object deleteComment(@RequestParam Integer id) {
        try {
            logger.info("-------------评论删除-------------");
            return goodsCommentService.deleteById(id);
        } catch (Exception e) {
            logger.info("评论删除失败:{}", e.getMessage());
            return WebResult.error("评论删除失败");
        }
    }

    /**
     * 评论列表
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/commentList")
    public WebResult commentList(@RequestBody String jsonBody) {
        logger.info("-------------评论列表-------------");
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        String goodsName = jsonObject.getString("goodsName");
        String nickname = jsonObject.getString("nickname");
        Integer page = jsonObject.getInteger("page");
        Integer limit = jsonObject.getInteger("limit");
        return goodsCommentService.commentList((page - 1) * limit, limit, goodsName, nickname);
    }

    /**
     * 评论数量
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/commentCount")
    public Integer commentCount(@RequestBody String jsonBody) {
        try {
            logger.info("-------------评论数量-------------");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody);
            String goodsName = jsonObject.getString("goodsName");
            String nickname = jsonObject.getString("nickname");
            return goodsCommentService.commentCount(goodsName, nickname);
        } catch (Exception e) {
            logger.info("评论数量失败:{}", e.getMessage());
            return 0;
        }
    }

}