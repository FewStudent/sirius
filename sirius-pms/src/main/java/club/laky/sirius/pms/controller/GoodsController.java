package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsService;
import club.laky.sirius.pms.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Goods)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-08 14:35:26
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    /**
     * 服务对象
     */
    @Resource
    private GoodsService service;


    @ResponseBody
    @RequestMapping("/add")
    public WebResult insert(@RequestBody String jsonBody) {
        try {
            logger.info("商品添加,参数为{}", jsonBody);
            return service.insertGoods(jsonBody);
        } catch (Exception e) {
            logger.error("商品添加失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/save")
    public WebResult save(@RequestBody String jsonBody) {
        try {
            logger.info("商品保存,参数为{}", jsonBody);
            return service.saveGoods(jsonBody);
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

}