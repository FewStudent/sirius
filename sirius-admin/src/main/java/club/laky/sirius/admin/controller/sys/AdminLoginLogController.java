package club.laky.sirius.admin.controller.sys;

import club.laky.sirius.admin.service.LoginLogService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录日志(LoginLog)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:41:13
 */
@RestController
@RequestMapping("/admin/loginLog")
public class AdminLoginLogController {
    private static final Logger logger = LoggerFactory.getLogger(AdminLoginLogController.class);

    /**
     * 服务对象
     */
    @Resource
    private LoginLogService loginLogService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有登录日志信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(loginLogService.queryListCount());
            layData.setData(loginLogService.queryList((page - 1) * limit, limit));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有登录日志信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除所有
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/deleteAll")
    public Object deleteAll() {
        try {
            logger.info("-------------删除所有-------------");
            return loginLogService.deleteAll();
        } catch (Exception e) {
            logger.info("删除所有失败:{}", e.getMessage());
            return WebResult.error("删除所有失败");
        }
    }

    /**
     * 删除一条
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        try {
            logger.info("-------------删除一条-------------");
            return loginLogService.deleteById(id);
        } catch (Exception e) {
            logger.info("删除一条失败:{}", e.getMessage());
            return WebResult.error("删除一条失败");
        }
    }

}