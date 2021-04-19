package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.SysPermission;
import club.laky.sirius.admin.service.SysPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 权限表(SysPermission)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:11:52
 */
@RestController
@RequestMapping("/admin/permission")
public class AdminSysPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysPermission selectOne(Integer id) {
        return this.sysPermissionService.queryById(id);
    }

}