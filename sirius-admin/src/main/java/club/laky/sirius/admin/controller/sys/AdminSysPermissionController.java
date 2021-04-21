package club.laky.sirius.admin.controller.sys;

import club.laky.sirius.admin.entity.SysPermission;
import club.laky.sirius.admin.service.SysPermissionService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AdminSysPermissionController.class);

    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有权限信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount());
            layData.setData(service.queryAllByLimit((page - 1) * limit, limit));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有权限信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        try {
            logger.info("-------------删除权限信息-------------");
            return WebResult.success(service.deleteById(id));
        } catch (Exception e) {
            logger.info("删除权限信息失败:{}", e.getMessage());
            return WebResult.error("删除权限信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody SysPermission permission) {
        try {
            logger.info("-------------添加权限信息-------------");
            return WebResult.success(service.insert(permission));
        } catch (Exception e) {
            logger.info("添加权限信息失败:{}", e.getMessage());
            return WebResult.error("添加权限信息失败");
        }
    }


    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody SysPermission permission) {
        try {
            logger.info("-------------修改权限信息-------------");
            return WebResult.success(service.update(permission));
        } catch (Exception e) {
            logger.info("修改权限信息失败:{}", e.getMessage());
            return WebResult.error("修改权限信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object all() {
        try {
            logger.info("-------------获取所有权限信息-------------");
            return WebResult.success(service.queryAll());
        } catch (Exception e) {
            logger.info("获取所有权限信息失败:{}", e.getMessage());
            return WebResult.error("获取所有权限信息失败");
        }
    }
}