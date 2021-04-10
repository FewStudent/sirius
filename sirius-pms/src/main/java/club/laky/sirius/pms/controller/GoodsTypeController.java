package club.laky.sirius.pms.controller;

import club.laky.sirius.pms.service.GoodsTypeService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GoodsType)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-08 20:04:18
 */
@RestController
@RequestMapping("/api/goodsType")
public class GoodsTypeController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsTypeService service;

    private static final Logger logger = LoggerFactory.getLogger(GoodsTypeController.class);


    @ResponseBody
    @RequestMapping("listCount")
    public int queryTypeListCount(@RequestBody String jsonBody) {
        try {
            logger.info("商品类型数量,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            String typeName = params.getString("typeName");
            return service.queryTypeListCount(typeName);
        } catch (Exception e) {
            logger.error("商品类型数量失败:{}", e.getMessage());
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping("list")
    public WebResult queryTypeList(@RequestBody String jsonBody) {
        try {
            logger.info("商品类型列表,参数为{}", jsonBody);
            JSONObject params = JSONObject.parseObject(jsonBody);
            Integer limit = params.getInteger("limit");
            Integer page = params.getInteger("page");
            String typeName = params.getString("typeName");
            return WebResult.success(service.queryTypeList((page - 1) * limit, limit, typeName));
        } catch (Exception e) {
            logger.error("商品类型保存失败:{}", e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object typeDelete(@RequestBody String jsonBody) {
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
    public Object typeSave(@RequestBody String jsonBody) {
        try {
            logger.info("-------------保存-------------");
            return service.save(jsonBody);
        } catch (Exception e) {
            logger.error("保存失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 所有类型
     */
    @ResponseBody
    @RequestMapping("allType")
    public Object allType() {
        try {
            logger.info("-------------所有类型-------------");
            return service.allType();
        } catch (Exception e) {
            logger.error("所有类型失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}