package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsCommentService;
import club.laky.sirius.pms.utils.WebResult;
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

}