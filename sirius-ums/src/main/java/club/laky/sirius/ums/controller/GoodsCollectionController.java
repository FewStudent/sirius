package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.entity.GoodsCollection;
import club.laky.sirius.ums.service.GoodsCollectionService;
import club.laky.sirius.ums.utils.WebResult;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (GoodsCollection)表控制层
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:33
 */
@RestController
@RequestMapping("/api/collection")
public class GoodsCollectionController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsCollectionService goodsCollectionService;

    private static final Logger logger = LoggerFactory.getLogger(GoodsCollectionController.class);


    /**
     * 获取用户收藏列表
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/collections")
    public Object collections(@RequestParam Integer userId) {
        try {
            logger.info("-------------获取用户收藏列表-------------");
            return goodsCollectionService.collections(userId);
        } catch (Exception e) {
            logger.info("获取用户收藏列表失败:{}", e.getMessage());
            return WebResult.error("获取用户收藏列表失败");
        }
    }

    /**
     * 删除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/deleteCollection")
    public Object deleteCollection(@RequestParam Integer id) {
        try {
            logger.info("-------------删除-------------");
            return goodsCollectionService.deleteCollection(id);
        } catch (Exception e) {
            logger.info("删除失败:{}", e.getMessage());
            return WebResult.error("删除失败");
        }
    }

    /**
     * 添加
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/insertCollection")
    public Object insertCollection(@RequestBody String jsonBody) {
        try {
            logger.info("-------------添加-------------");
            return goodsCollectionService.insertCollection(jsonBody);
        } catch (Exception e) {
            logger.info("添加失败:{}", e.getMessage());
            return WebResult.error("添加失败");
        }
    }

    /**
     * 是否收藏商品
     */
    @ResponseBody
    @RequestMapping("hasCollect")
    public Object hasCollect(@RequestParam Integer userId, @RequestParam Integer goodsId) {
        try {
            logger.info("-------------是否收藏商品-------------");
            Integer result = goodsCollectionService.hasCollect(goodsId, userId);
            if (result != null) {
                return WebResult.success(result);
            }
            return WebResult.error("未收藏");
        } catch (Exception e) {
            logger.error("是否收藏商品失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 搜索所有收藏
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/adminCollectionList")
    public WebResult adminCollectionList(@RequestBody String jsonBody) {
        try {
            logger.info("-------------搜索所有收藏-------------");
            return goodsCollectionService.adminCollectionList(jsonBody);
        } catch (Exception e) {
            logger.info("搜索所有收藏失败:{}", e.getMessage());
            return WebResult.error("搜索所有收藏失败");
        }
    }


    /**
     * 收藏数量
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/adminCollectionCount")
    public Object adminCollectionCount(@RequestBody String jsonBody) {
        try {
            logger.info("-------------收藏数量-------------");
            return goodsCollectionService.adminCollectionCount(jsonBody);
        } catch (Exception e) {
            logger.info("收藏数量失败:{}", e.getMessage());
            return WebResult.error("收藏数量失败");
        }
    }

    /**
     * 取消收藏
     */
    @ResponseBody
    @RequestMapping("cancelCollection")
    public Object cancelCollection(@RequestParam Integer goodsId, @RequestParam Integer userId) {
        try {
            logger.info("-------------取消收藏-------------");
            return WebResult.success(goodsCollectionService.deleteByTwoId(userId, goodsId));
        } catch (Exception e) {
            logger.error("取消收藏失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }
}