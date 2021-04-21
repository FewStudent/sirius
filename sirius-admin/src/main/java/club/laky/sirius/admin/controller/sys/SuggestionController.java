package club.laky.sirius.admin.controller.sys;

import club.laky.sirius.admin.entity.Suggestion;
import club.laky.sirius.admin.service.SuggestionService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Suggestion)表控制层
 *
 * @author lakyjpan
 * @since 2021-04-22 00:06:38
 */
@RestController
@RequestMapping("/admin/suggestion")
public class SuggestionController {
    /**
     * 服务对象
     */
    @Resource
    private SuggestionService suggestionService;

    private static final Logger logger = LoggerFactory.getLogger(AdminSysJobController.class);


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Suggestion selectOne(Integer id) {
        return this.suggestionService.queryById(id);
    }


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有建议信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(suggestionService.queryListCount());
            layData.setData(suggestionService.queryList((page - 1) * limit, limit));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有建议信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id) {
        try {
            logger.info("-------------删除建议信息-------------");
            return WebResult.success(suggestionService.deleteById(id));
        } catch (Exception e) {
            logger.info("删除建议信息失败:{}", e.getMessage());
            return WebResult.error("删除建议信息失败");
        }
    }

    @ResponseBody
    @RequestMapping("/check")
    public Object update(@RequestParam Integer id) {
        try {
            logger.info("-------------修改建议信息-------------");
            return WebResult.success(suggestionService.updateById(id));
        } catch (Exception e) {
            logger.info("修改建议信息失败:{}", e.getMessage());
            return WebResult.error("修改建议信息失败");
        }
    }
}