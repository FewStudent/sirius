package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.service.GoodsService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/8 11:43
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);


    @Autowired
    private GoodsService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String goodsName,
                             Integer brandId, Integer typeId, Integer state) {
        try {
            logger.info("-------------查询所有管理员信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryGoodsListCount(goodsName, brandId, typeId, state));
            layData.setData(service.queryGoodsList((page - 1) * limit, limit, goodsName, brandId, typeId, state));
            return layData;
        } catch (Exception e) {
            logger.info("查询所有管理员信息失败:{}", e.getMessage());
            return null;
        }
    }

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
