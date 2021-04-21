package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsBrandService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 品牌商表(GoodsBrand)表控制层
 *
 * @author lakyjpan
 * @since 2021-04-08 21:45:09
 */
@RestController
@RequestMapping("/api/goodsBrand")
public class GoodsBrandController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsBrandController.class);

    /**
     * 服务对象
     */
    @Resource
    private GoodsBrandService service;

    @ResponseBody
    @RequestMapping("listCount")
    public int queryBrandListCount(@RequestBody String jsonBody) {
        try {
            logger.info("品牌数量,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            String brandName = params.getString("brandName");
            return service.queryBrandListCount(brandName);
        } catch (Exception e) {
            logger.error("品牌数量失败:{}", e.getMessage());
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping("list")
    public WebResult queryBrandList(@RequestBody String jsonBody) {
        try {
            logger.info("品牌列表,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            Integer limit = params.getInteger("limit");
            Integer page = params.getInteger("page");
            String brandName = params.getString("brandName");
            return WebResult.success(service.queryBrandList((page - 1) * limit, limit, brandName));
        } catch (Exception e) {
            logger.error("品牌保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object brandDelete(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除-------------");
            boolean result = service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (result) {
                return WebResult.success("删除成功");
            }
            return WebResult.error("删除失败");
        } catch (Exception e) {
            logger.error("删除失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("save")
    public Object brandSave(@RequestBody String jsonBody) {
        try {
            logger.info("-------------保存-------------");
            return service.save(jsonBody);
        } catch (Exception e) {
            logger.error("保存失败：" + e.getMessage());
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
            return service.allBrand();
        } catch (Exception e) {
            logger.error("所有品牌失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object brandDetail(@RequestParam Integer id) {
        try {
            logger.info("-------------详情-------------");
            return service.detail(id);
        } catch (Exception e) {
            logger.error("详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}