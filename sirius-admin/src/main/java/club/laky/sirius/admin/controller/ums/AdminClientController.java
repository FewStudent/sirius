package club.laky.sirius.admin.controller.ums;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/8 20:47
 */
@RestController
@RequestMapping("/admin/client")
public class AdminClientController {
    private static final Logger logger = LoggerFactory.getLogger(AdminClientController.class);

    @Autowired
    private FeignClientService clientService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String phone, String nickname, Integer state) {
        try {
            logger.info("-------------查询所有用户信息-------------");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("nickname", nickname);
            jsonBody.put("phone", phone);
            jsonBody.put("state", state);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(clientService.queryClientCount(jsonBody.toJSONString()));
            layData.setData((List) clientService.queryClientList(jsonBody.toJSONString()));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有用户信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 修改用户信息
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody String jsonBody) {
        try {
            logger.info("-------------修改用户信息-------------");
            return WebResult.success(clientService.updateUser(jsonBody));
        } catch (Exception e) {
            logger.error("修改用户信息失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}
