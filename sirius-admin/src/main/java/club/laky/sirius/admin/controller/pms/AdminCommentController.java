package club.laky.sirius.admin.controller.pms;

import club.laky.sirius.admin.feign.FeignGoodsService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/19 16:12
 */
@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCommentController.class);
    @Autowired
    private FeignGoodsService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String goodsName, String nickname) {
        try {
            logger.info("-------------查询所有商品评论信息-------------");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("goodsName", goodsName);
            jsonBody.put("nickname", nickname);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.commentCount(jsonBody.toJSONString()));
            layData.setData((List) service.commentList(jsonBody.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有商品评论信息失败:{}", e.getMessage());
            return null;
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
            return service.reply(jsonBody);
        } catch (Exception e) {
            logger.info("评论回复失败:{}", e.getMessage());
            return WebResult.error("评论回复失败");
        }
    }

}

