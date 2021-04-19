package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.SysDepartment;
import club.laky.sirius.admin.service.SysDepartmentService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 部门(SysDepartment)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-08 19:43:48
 */
@RestController
@RequestMapping("/admin/department")
public class AdminSysDepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(AdminSysDepartmentController.class);

    /**
     * 服务对象
     */
    @Resource
    private SysDepartmentService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String departmentName) {
        try {
            logger.info("-------------查询所有部门信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryListCount(departmentName));
            layData.setData(service.queryList((page - 1) * limit, limit, departmentName));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有部门信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id) {
        try {
            logger.info("-------------删除部门信息-------------");
            return WebResult.success(service.deleteById(id));
        } catch (Exception e) {
            logger.info("删除部门信息失败:{}", e.getMessage());
            return WebResult.error("删除部门信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(SysDepartment department) {
        try {
            logger.info("-------------添加部门信息-------------");
            return WebResult.success(service.insert(department));
        } catch (Exception e) {
            logger.info("添加部门信息失败:{}", e.getMessage());
            return WebResult.error("添加部门信息失败");
        }
    }


    @ResponseBody
    @RequestMapping("/update")
    public Object update(SysDepartment department) {
        try {
            logger.info("-------------修改部门信息-------------");
            return WebResult.success(service.update(department));
        } catch (Exception e) {
            logger.info("修改部门信息失败:{}", e.getMessage());
            return WebResult.error("修改部门信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object all() {
        try {
            logger.info("-------------获取所有部门信息-------------");
            return WebResult.success(service.queryAll());
        } catch (Exception e) {
            logger.info("获取所有部门信息失败:{}", e.getMessage());
            return WebResult.error("获取所有部门信息失败");
        }
    }
}