package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.service.SysUserService;
import club.laky.sirius.admin.utils.LayuiVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/manager")
public class AdminManagerController {

    private static final Logger logger = LoggerFactory.getLogger(AdminManagerController.class);

    @Autowired
    private SysUserService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String nickname,
                             Integer departmentId, Integer jobId, Integer state) {
        try {
            logger.info("-------------查询所有管理员信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryAdminListCount(nickname, departmentId, jobId, state));//总数
            layData.setData(service.queryAdminList((page - 1) * limit, limit, nickname, departmentId, jobId, state));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有管理员信息失败:{}", e.getMessage());
            return null;
        }
    }

}
