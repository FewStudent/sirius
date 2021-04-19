package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.service.SysRoleService;
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
 */
@Controller
@RequestMapping("/admin/manager")
public class AdminManagerController {

    private static final Logger logger = LoggerFactory.getLogger(AdminManagerController.class);

    @Autowired
    private FeignClientService clientService;

    @Autowired
    private SysRoleService sysRoleService;

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
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("nickname", nickname);
            jsonBody.put("departmentId", departmentId);
            jsonBody.put("jobId", jobId);
            jsonBody.put("state", state);

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

    /**
     * 修改管理员信息
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody String jsonBody) {
        try {
            logger.info("-------------修改管理员信息-------------");
            return clientService.update(jsonBody);
        } catch (Exception e) {
            logger.info("修改管理员信息失败:{}", e.getMessage());
            return WebResult.error("修改管理员信息失败");
        }
    }

    /**
     * 删除管理员
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer userId) {
        try {
            logger.info("-------------删除管理员-------------");
            return clientService.delete(userId);
        } catch (Exception e) {
            logger.info("删除管理员失败:{}", e.getMessage());
            return WebResult.error("删除管理员失败");
        }
    }

    /**
     * 添加管理员信息
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody String jsonBody) {
        try {
            logger.info("-------------添加管理员信息-------------");
            return clientService.insertManager(jsonBody);
        } catch (Exception e) {
            logger.info("添加管理员信息失败:{}", e.getMessage());
            return WebResult.error("添加管理员信息失败");
        }
    }

    /**
     * 用户详情
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/detail")
    public Object detail(@RequestParam String account) {
        try {
            logger.info("-------------用户详情-------------");
            return clientService.detail(account, 0);
        } catch (Exception e) {
            logger.info("用户详情失败:{}", e.getMessage());
            return WebResult.error("用户详情失败");
        }
    }

    /**
     * 保存用户角色
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/saveRole")
    public Object saveRole(@RequestBody String jsonBody) {
        try {
            logger.info("-------------保存用户角色-------------");
            return sysRoleService.saveRole(jsonBody);
        } catch (Exception e) {
            logger.info("保存用户角色失败:{}", e.getMessage());
            return WebResult.error("保存用户角色失败");
        }
    }

    /**
     * 查看用户的角色列表
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/roleList")
    public Object roleList(@RequestParam Integer userId) {
        try {
            logger.info("-------------查看用户的角色列表-------------");
            return sysRoleService.queryByUserId(userId);
        } catch (Exception e) {
            logger.info("查看用户的角色列表失败:{}", e.getMessage());
            return WebResult.error("查看用户的角色列表失败");
        }
    }

}
