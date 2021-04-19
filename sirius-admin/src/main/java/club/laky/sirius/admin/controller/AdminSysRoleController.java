package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.SysRole;
import club.laky.sirius.admin.entity.SysRole;
import club.laky.sirius.admin.service.SysPermissionService;
import club.laky.sirius.admin.service.SysRoleService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AdminSysRoleController.class);


    /**
     * 服务对象
     */
    @Resource
    private SysRoleService service;

    @Resource
    private SysPermissionService permissionService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有角色信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount());
            layData.setData(service.queryAllByLimit((page - 1) * limit, limit));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有角色信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id) {
        try {
            logger.info("-------------删除角色信息-------------");
            return WebResult.success(service.deleteById(id));
        } catch (Exception e) {
            logger.info("删除角色信息失败:{}", e.getMessage());
            return WebResult.error("删除角色信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(SysRole role) {
        try {
            logger.info("-------------添加角色信息-------------");
            return WebResult.success(service.insert(role));
        } catch (Exception e) {
            logger.info("添加角色信息失败:{}", e.getMessage());
            return WebResult.error("添加角色信息失败");
        }
    }


    @ResponseBody
    @RequestMapping("/update")
    public Object update(SysRole role) {
        try {
            logger.info("-------------修改角色信息-------------");
            return WebResult.success(service.update(role));
        } catch (Exception e) {
            logger.info("修改角色信息失败:{}", e.getMessage());
            return WebResult.error("修改角色信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object all() {
        try {
            logger.info("-------------获取所有角色信息-------------");
            return WebResult.success(service.queryAll());
        } catch (Exception e) {
            logger.info("获取所有角色信息失败:{}", e.getMessage());
            return WebResult.error("获取所有角色信息失败");
        }
    }

    /**
     * 保存角色权限
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/savePermission")
    public Object savePermission(@RequestBody String jsonBody) {
        try {
            logger.info("-------------保存角色权限-------------");
            return service.savePermission(jsonBody);
        } catch (Exception e) {
            logger.info("保存角色权限失败:{}", e.getMessage());
            return WebResult.error("保存角色权限失败");
        }
    }

    /**
     * 查看角色的权限列表
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/permissionList")
    public Object roleList(@RequestParam Integer roleId) {
        try {
            logger.info("-------------查看角色的权限列表-------------");
            return permissionService.queryByRoleId(roleId);
        } catch (Exception e) {
            logger.info("查看角色的权限列表失败:{}", e.getMessage());
            return WebResult.error("查看角色的权限列表失败");
        }
    }
}