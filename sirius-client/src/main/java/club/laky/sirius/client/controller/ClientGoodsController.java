package club.laky.sirius.client.controller;

import club.laky.sirius.client.feign.FeignGoodsService;
import club.laky.sirius.client.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ClientGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(ClientGoodsController.class);

    @Autowired
    private FeignGoodsService goodsService;

    /**
     * 上架中的商品列表
     */
    @ResponseBody
    @RequestMapping("/common/goods/allOnSale")
    public Object allOnSale(@RequestBody String jsonBody) {
        try {
            logger.info("-------------上架中的商品列表-------------");
            return goodsService.allOnSale(jsonBody);
        } catch (Exception e) {
            logger.error("上架中的商品列表失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 所有选项类型
     */
    @ResponseBody
    @RequestMapping("/common/goods/allSelect")
    public Object allSelect() {
        try {
            logger.info("-------------所有选项类型-------------");
            return goodsService.allSelect();
        } catch (Exception e) {
            logger.error("所有选项类型失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品详情
     */
    @ResponseBody
    @RequestMapping("/common/goods/detail")
    public Object goodsDetail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------商品详情-------------");
            return goodsService.goodsDetail(JSONObject.parseObject(jsonBody).getInteger("goodsId"));
        } catch (Exception e) {
            logger.error("获取商品详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 获取商品评论
     */
    @ResponseBody
    @RequestMapping("/common/goods/comments")
    public Object getCommentsF(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取商品评论-------------");
            return goodsService.getCommentByGoodsId(JSONObject.parseObject(jsonBody).getInteger("goodsId"));
        } catch (Exception e) {
            logger.error("获取商品评论失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

}
