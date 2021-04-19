package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.entity.ImgLog;
import club.laky.sirius.admin.service.ImgLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 图片上传记录(ImgLog)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:40:55
 */
@RestController
@RequestMapping("/admin/imgLog")
public class AdminImgLogController {
    /**
     * 服务对象
     */
    @Resource
    private ImgLogService imgLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ImgLog selectOne(Integer id) {
        return this.imgLogService.queryById(id);
    }

}