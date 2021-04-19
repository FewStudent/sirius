package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.SysRole;
import club.laky.sirius.admin.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表(SysRole)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:11:09
 */
@RestController
@RequestMapping("/admin/role")
public class AdminSysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRole selectOne(Integer id) {
        return this.sysRoleService.queryById(id);
    }

}