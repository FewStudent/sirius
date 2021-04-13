package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.entity.GoodsCollection;
import club.laky.sirius.pms.service.GoodsCollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GoodsCollection)表控制层
 *
 * @author lakyjpan
 * @since 2021-04-14 00:32:15
 */
@RestController
@RequestMapping("/api/collection")
public class GoodsCollectionController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsCollectionService goodsCollectionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GoodsCollection selectOne(Integer id) {
        return this.goodsCollectionService.queryById(id);
    }

}