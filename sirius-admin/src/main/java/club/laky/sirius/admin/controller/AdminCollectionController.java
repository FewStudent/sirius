package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/19 18:36
 */
@Controller
@RequestMapping("/admin/collection")
public class AdminCollectionController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCollectionController.class);

    @Autowired
    private FeignClientService clientService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String nickname) {
        try {
            logger.info("-------------查询所有用户收藏信息-------------");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("nickname", nickname);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(clientService.adminCollectionCount(jsonBody.toJSONString()));
            layData.setData((List) clientService.adminCollectionList(jsonBody.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有用户收藏信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 刪除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        try {
            logger.info("-------------刪除-------------");
            return clientService.deleteCollection(id);
        } catch (Exception e) {
            logger.info("刪除失败:{}", e.getMessage());
            return WebResult.error("刪除失败");
        }
    }

}
