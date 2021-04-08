package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.utils.LayuiVO;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 */
@Controller
@RequestMapping("/admin/manager")
public class AdminManagerController {

    private static final Logger logger = LoggerFactory.getLogger(AdminManagerController.class);

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
            logger.info("-------------查询所有管理员信息-------------");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit",limit);
            jsonBody.put("page",page);
            jsonBody.put("nickname",nickname);
            jsonBody.put("departmentId",departmentId);
            jsonBody.put("jobId",jobId);
            jsonBody.put("state",state);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(clientService.queryAdminCount(jsonBody.toJSONString()));
            layData.setData((List) clientService.queryAdminList(jsonBody.toJSONString()));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有管理员信息失败:{}", e.getMessage());
            return null;
        }
    }



}
