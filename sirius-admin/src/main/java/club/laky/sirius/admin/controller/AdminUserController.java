package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/19 13:30
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private FeignClientService clientService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String nickname,
                             Integer departmentId, Integer jobId, Integer state) {
        try {
            logger.info("-------------查询所有会员信息-------------");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("nickname", nickname);
            jsonBody.put("departmentId", departmentId);
            jsonBody.put("jobId", jobId);
            jsonBody.put("state", state);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(clientService.queryUserCount(jsonBody.toJSONString()));
            layData.setData((List) clientService.queryUserList(jsonBody.toJSONString()));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有会员信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 用户删除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer userId) {
        try {
            logger.info("-------------用户删除-------------");
            return clientService.delete(userId);
        } catch (Exception e) {
            logger.info("用户删除失败:{}", e.getMessage());
            return WebResult.error("用户删除失败");
        }
    }

    /**
     * 用户修改
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody String jsonBody) {
        try {
            logger.info("-------------用户修改-------------");
            return clientService.update(jsonBody);
        } catch (Exception e) {
            logger.info("用户修改失败:{}", e.getMessage());
            return WebResult.error("用户修改失败");
        }
    }


}
