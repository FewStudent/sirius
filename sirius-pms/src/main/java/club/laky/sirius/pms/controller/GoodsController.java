package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsBrandService;
import club.laky.sirius.pms.service.GoodsService;
import club.laky.sirius.pms.service.GoodsTypeService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private GoodsBrandService goodsBrandService;

    @Resource
    private GoodsTypeService goodsTypeService;


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
    public int queryGoodsListCount(@RequestBody String jsonBody) {
        try {
            logger.info("商品数量,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            String goodsName = params.getString("goodsName");
            Integer brandId = params.getInteger("brandId");
            Integer typeId = params.getInteger("typeId");
            Integer state = params.getInteger("state");
            return service.queryGoodsListCount(goodsName, brandId, typeId, state);
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping("list")
    public WebResult queryGoodsList(@RequestBody String jsonBody) {
        try {
            logger.info("商品列表,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            Integer limit = params.getInteger("limit");
            Integer page = params.getInteger("page");
            String goodsName = params.getString("goodsName");
            Integer brandId = params.getInteger("brandId");
            Integer typeId = params.getInteger("typeId");
            Integer state = params.getInteger("state");
            return WebResult.success(service.queryGoodsList((page - 1) * limit, limit, goodsName, brandId, typeId, state));
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品删除
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(@RequestParam Integer goodsId) {
        try {
            logger.info("-------------商品删除-------------");
            return service.delete(goodsId);
        } catch (Exception e) {
            logger.error("商品删除失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品上架
     */
    @ResponseBody
    @RequestMapping("onSale")
    public Object on(@RequestParam Integer goodsId) {
        try {
            logger.info("-------------商品上架-------------");
            return service.onSale(goodsId);
        } catch (Exception e) {
            logger.error("商品上架失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 下架
     */
    @ResponseBody
    @RequestMapping("offShelf")
    public Object down(@RequestParam Integer goodsId) {
        try {
            logger.info("-------------下架-------------");
            return service.offShelf(goodsId);
        } catch (Exception e) {
            logger.error("下架失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object goodsDetail(@RequestParam Integer goodsId) {
        try {
            logger.info("-------------商品详情-------------");
            return service.detail(goodsId);
        } catch (Exception e) {
            logger.error("获取商品详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品所有下拉框
     */
    @ResponseBody
    @RequestMapping("allSelect")
    public Object allSelect() {
        try {
            logger.info("-------------商品所有下拉框-------------");
            Map<String, Object> result = new HashMap<>();
            result.put("types", goodsTypeService.allType());
            result.put("brands", goodsBrandService.allBrand());
            return WebResult.success(result);
        } catch (Exception e) {
            logger.error("商品所有下拉框失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 获取已上架的商品
     */
    @ResponseBody
    @RequestMapping("allOnSale")
    public Object allOnSale(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取已上架的商品-------------");
            return service.allOnSale(jsonBody);
        } catch (Exception e) {
            logger.error("获取已上架的商品失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}