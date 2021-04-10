package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
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
    public WebResult goodsInsert(@RequestBody String jsonBody) {
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
    public WebResult goodsSave(@RequestBody String jsonBody) {
        try {
            logger.info("商品保存,参数为{}", jsonBody);
            return service.saveGoods(jsonBody);
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("listCount")
    public int queryGoodsListCount(@RequestBody String jsonBody){
        try {
            logger.info("商品数量,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            String goodsName = params.getString("goodsName");
            Integer brandId = params.getInteger("brandId");
            Integer typeId = params.getInteger("typeId");
            Integer state = params.getInteger("state");
            return service.queryGoodsListCount(goodsName,brandId,typeId,state);
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return 0;
        }
    }
    @ResponseBody
    @RequestMapping("list")
    public WebResult queryGoodsList(@RequestBody String jsonBody){
        try {
            logger.info("商品列表,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            Integer limit = params.getInteger("limit");
            Integer page = params.getInteger("page");
            String goodsName = params.getString("goodsName");
            Integer brandId = params.getInteger("brandId");
            Integer typeId = params.getInteger("typeId");
            Integer state = params.getInteger("state");
            return WebResult.success(service.queryGoodsList((page-1)*limit,limit,goodsName,brandId,typeId,state));
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

}