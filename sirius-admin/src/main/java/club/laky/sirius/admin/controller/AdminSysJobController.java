package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.SysJob;
import club.laky.sirius.admin.service.SysJobService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 岗位(SysJob)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-08 19:44:03
 */
@RestController
@RequestMapping("/admin/job")
public class AdminSysJobController {
    private static final Logger logger = LoggerFactory.getLogger(AdminSysJobController.class);
    /**
     * 服务对象
     */
    @Resource
    private SysJobService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String jobName) {
        try {
            logger.info("-------------查询所有岗位信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryListCount(jobName));
            layData.setData(service.queryList((page - 1) * limit, limit, jobName));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有岗位信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id) {
        try {
            logger.info("-------------删除岗位信息-------------");
            return WebResult.success(service.deleteById(id));
        } catch (Exception e) {
            logger.info("删除岗位信息失败:{}", e.getMessage());
            return WebResult.error("删除岗位信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(SysJob job) {
        try {
            logger.info("-------------添加岗位信息-------------");
            return WebResult.success(service.insert(job));
        } catch (Exception e) {
            logger.info("添加岗位信息失败:{}", e.getMessage());
            return WebResult.error("添加岗位信息失败");
        }
    }


    @ResponseBody
    @RequestMapping("/update")
    public Object update(SysJob job) {
        try {
            logger.info("-------------修改岗位信息-------------");
            return WebResult.success(service.update(job));
        } catch (Exception e) {
            logger.info("修改岗位信息失败:{}", e.getMessage());
            return WebResult.error("修改岗位信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object all() {
        try {
            logger.info("-------------修改各位信息-------------");
            return WebResult.success(service.queryAll());
        } catch (Exception e) {
            logger.info("修改各位信息失败:{}", e.getMessage());
            return WebResult.error("修改各位信息失败");
        }
    }

}