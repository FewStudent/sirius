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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/19 15:18
 */
@Controller
@RequestMapping("/admin/goodsEx")
public class AdminGoodsExController {

    private static final Logger logger = LoggerFactory.getLogger(AdminGoodsExController.class);

    @Autowired
    private FeignGoodsService goodsService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/brandList")
    public LayuiVO brandList(Integer page, Integer limit, String brandName) {
        try {
            logger.info("-------------查询所有品牌信息-------------");

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("brandName", brandName);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(goodsService.queryBrandListCount(jsonBody.toJSONString()));
            layData.setData((List) goodsService.queryBrandList(jsonBody.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有品牌信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/deleteBrand")
    public Object delete(@RequestParam Integer id) {
        logger.info("-------------删除品牌信息-------------");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        return WebResult.success(goodsService.brandDelete(jsonObject.toJSONString()));
    }

    @ResponseBody
    @RequestMapping("/saveBrand")
    public Object saveBrand(@RequestBody String jsonBody) {
        try {
            logger.info("-------------保存品牌信息-------------");
            return WebResult.success(goodsService.brandSave(jsonBody));
        } catch (Exception e) {
            logger.info("保存品牌信息失败:{}", e.getMessage());
            return WebResult.error("保存品牌信息失败");
        }
    }


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/typeList")
    public LayuiVO typeList(Integer page, Integer limit, String typeName) {
        try {
            logger.info("-------------查询所有商品类型信息-------------");

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("limit", limit);
            jsonBody.put("page", page);
            jsonBody.put("typeName", typeName);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(goodsService.queryTypeListCount(jsonBody.toJSONString()));
            layData.setData((List) goodsService.queryTypeList(jsonBody.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有商品类型信息失败:{}", e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/deleteType")
    public Object deleteType(@RequestParam Integer id) {
        logger.info("-------------删除品牌信息-------------");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        return WebResult.success(goodsService.typeDelete(jsonObject.toJSONString()));
    }

    @ResponseBody
    @RequestMapping("/saveType")
    public Object saveType(@RequestBody String jsonBody) {
        try {
            logger.info("-------------保存商品类型信息-------------");
            return WebResult.success(goodsService.typeSave(jsonBody));
        } catch (Exception e) {
            logger.info("保存商品类型信息失败:{}", e.getMessage());
            return WebResult.error("保存商品类型信息失败");
        }
    }

    /**
     * 类型详情
     */
    @ResponseBody
    @RequestMapping("typeDetail")
    public Object typeDetail(@RequestParam Integer id) {
        try {
            logger.info("-------------类型详情-------------");
            return WebResult.success(goodsService.typeDetail(id));
        } catch (Exception e) {
            logger.error("类型详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 品牌详情
     */
    @ResponseBody
    @RequestMapping("brandDetail")
    public Object brandDetail(@RequestParam Integer id) {
        try {
            logger.info("-------------品牌详情-------------");
            return WebResult.success(goodsService.brandDetail(id));
        } catch (Exception e) {
            logger.error("品牌详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

}
