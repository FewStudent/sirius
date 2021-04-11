package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignGoodsService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/8 11:43
 */
@Controller
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(AdminGoodsController.class);


    @Autowired
    private FeignGoodsService goodsService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String goodsName,
                             Integer brandId, Integer typeId, Integer state) {
        try {
            logger.info("-------------查询所有商品信息-------------");

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("goodsName", goodsName);
            jsonBody.put("brandId", brandId);
            jsonBody.put("typeId", typeId);
            jsonBody.put("state", state);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(goodsService.queryGoodsListCount(jsonBody.toJSONString()));
            layData.setData((List) goodsService.queryGoodsList(jsonBody.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有商品信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/add")
    public WebResult insert(@RequestBody String jsonBody) {
        try {
            logger.info("商品添加,参数为{}", jsonBody);
            return goodsService.goodsInsert(jsonBody);
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
            return goodsService.goodsSave(jsonBody);
        } catch (Exception e) {
            logger.error("商品保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 所有商品类型
     */
    @ResponseBody
    @RequestMapping("allType")
    public Object allType() {
        try {
            logger.info("-------------所有商品类型-------------");
            return goodsService.allType();
        } catch (Exception e) {
            logger.error("所有商品类型失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 所有品牌
     */
    @ResponseBody
    @RequestMapping("allBrand")
    public Object allBrand() {
        try {
            logger.info("-------------所有品牌-------------");
            return goodsService.allBrand();
        } catch (Exception e) {
            logger.error("所有品牌失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 上架
     */
    @ResponseBody
    @RequestMapping("onSale")
    public Object onSale(@RequestBody String jsonBody) {
        try {
            logger.info("-------------上架-------------");
            logger.info("请求参数:{}", jsonBody);
            return goodsService.on(JSONObject.parseObject(jsonBody).getInteger("goodsId"));
        } catch (Exception e) {
            logger.error("上架失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 上架
     */
    @ResponseBody
    @RequestMapping("offShelf")
    public Object offShelf(@RequestBody String jsonBody) {
        try {
            logger.info("-------------下架-------------");
            logger.info("请求参数:{}", jsonBody);
            return goodsService.down(JSONObject.parseObject(jsonBody).getInteger("goodsId"));
        } catch (Exception e) {
            logger.error("下架失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 删除商品
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除商品-------------");
            logger.info("请求参数:{}", jsonBody);
            return goodsService.delete(JSONObject.parseObject(jsonBody).getInteger("goodsId"));
        } catch (Exception e) {
            logger.error("删除商品失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 获取商品所有下拉框
     */
    @ResponseBody
    @RequestMapping("allSelect")
    public Object allSelect() {
        try {
            logger.info("-------------获取商品所有下拉框-------------");
            return goodsService.allSelect();
        } catch (Exception e) {
            logger.error("获取商品所有下拉框失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 商品详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------商品详情-------------");
            return goodsService.goodsDetail(JSONObject.parseObject(jsonBody).getInteger("goodsId"));
        } catch (Exception e) {
            logger.error("商品详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}
