package club.laky.sirius.client.controller;

import club.laky.sirius.client.entity.Suggestion;
import club.laky.sirius.client.entity.SysUser;
import club.laky.sirius.client.feign.FeignCacheService;
import club.laky.sirius.client.feign.FeignClientService;
import club.laky.sirius.client.service.SuggestionService;
import club.laky.sirius.client.utils.WebResult;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/client/user")
public class ClientUserController {

    private static final Logger logger = LoggerFactory.getLogger(ClientUserController.class);

    @Autowired
    private FeignClientService clientService;
    @Autowired
    private FeignCacheService cacheService;
    @Autowired
    private SuggestionService suggestionService;

    /**
     * 获取用户的所有地址
     */
    @ResponseBody
    @RequestMapping("addressList")
    public Object addressList(HttpServletRequest request) {
        try {
            logger.info("-------------获取用户的所有地址-------------");
            return clientService.userAddressList(getUserId(request));
        } catch (Exception e) {
            logger.error("获取用户的所有地址失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 更新用户信息
     */
    @ResponseBody
    @RequestMapping("updateInfo")
    public Object updateInfo(HttpServletRequest request, @RequestBody String jsonBody) {
        try {
            logger.info("-------------更新用户信息-------------");
            String token = request.getHeader("token");
            Integer result = clientService.updateUser(jsonBody);
            if (result == 1) {
                cacheService.del(token);
                return WebResult.success("修改成功");
            }
            return WebResult.error("修改失败");
        } catch (Exception e) {
            logger.error("更新用户信息失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 当前用户详情
     */
    @ResponseBody
    @RequestMapping("userInfo")
    public Object userInfo(HttpServletRequest request) {
        try {
            logger.info("-------------当前用户详情-------------");
            String token = request.getHeader("token");
            if (!StringUtils.isEmpty(token)) {
                Map<String, Object> data = (Map<String, Object>) cacheService.get(token);
                SysUser user = JSON.parseObject((String) data.get("data"), SysUser.class);
                if (user != null) {
                    return WebResult.success(user);
                }
            }
            return WebResult.error("获取失败");
        } catch (Exception e) {
            logger.error("当前用户详情失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 发表意见
     */
    @ResponseBody
    @RequestMapping("insertSuggestion")
    public Object insertSuggestion(HttpServletRequest request, @RequestBody Suggestion suggestion) {
        try {
            logger.info("-------------发表意见-------------");
            suggestion.setState(0);
            suggestion.setCreateTime(DateUtil.now());
            suggestion.setUserId(getUserId(request));
            return WebResult.success(suggestionService.insert(suggestion));
        } catch (Exception e) {
            logger.error("发表意见失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 是否收藏商品
     */
    @ResponseBody
    @RequestMapping("hasCollect")
    public Object hasCollect(HttpServletRequest request, @RequestParam Integer goodsId) {
        try {
            logger.info("-------------是否收藏商品-------------");
            return clientService.hasCollect(getUserId(request), goodsId);
        } catch (Exception e) {
            logger.error("是否收藏商品失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 添加收藏
     */
    @ResponseBody
    @RequestMapping("insertCollection")
    public Object insertCollection(HttpServletRequest req, @RequestParam Integer goodsId) {
        try {
            logger.info("-------------添加收藏-------------");
            JSONObject object = new JSONObject();
            object.put("goodsId", goodsId);
            object.put("userId", getUserId(req));
            return clientService.insertCollection(object.toJSONString());
        } catch (Exception e) {
            logger.error("添加收藏失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 取消收藏
     */
    @ResponseBody
    @RequestMapping("cancelCollection")
    public Object cancelCollection(HttpServletRequest request, @RequestParam Integer goodsId) {
        try {
            logger.info("-------------取消收藏-------------");
            return clientService.cancelCollection(goodsId, getUserId(request));
        } catch (Exception e) {
            logger.error("取消收藏失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 取消收藏
     */
    @ResponseBody
    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestParam Integer id) {
        try {
            logger.info("-------------取消收藏-------------");
            return clientService.deleteCollection(id);
        } catch (Exception e) {
            logger.error("取消收藏失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }


    /**
     * 获取收藏列表
     */
    @ResponseBody
    @RequestMapping("collections")
    public Object collections(HttpServletRequest req) {
        try {
            logger.info("-------------获取收藏列表-------------");
            return clientService.collections(getUserId(req));
        } catch (Exception e) {
            logger.error("获取收藏列表失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    private Integer getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            Map<String, Object> data = (Map<String, Object>) cacheService.get(token);
            SysUser user = JSON.parseObject((String) data.get("data"), SysUser.class);
            return user.getId();
        }
        return null;
    }
}
