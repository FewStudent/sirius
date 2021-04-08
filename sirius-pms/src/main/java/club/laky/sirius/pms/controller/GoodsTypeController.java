package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.entity.GoodsType;
import club.laky.sirius.pms.service.GoodsTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GoodsType)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-08 20:04:18
 */
@RestController
@RequestMapping("goodsType")
public class GoodsTypeController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsTypeService goodsTypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GoodsType selectOne(Integer id) {
        return this.goodsTypeService.queryById(id);
    }

}