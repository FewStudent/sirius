package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.LoginLog;
import club.laky.sirius.admin.service.LoginLogService;
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
    /**
     * 服务对象
     */
    @Resource
    private LoginLogService loginLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public LoginLog selectOne(Integer id) {
        return this.loginLogService.queryById(id);
    }

}