package club.laky.sirius.client.controller;

import club.laky.sirius.client.entity.SysUser;
import club.laky.sirius.client.feign.FeignCacheService;
import club.laky.sirius.client.feign.FeignClientService;
import club.laky.sirius.client.service.SuggestionService;
import club.laky.sirius.client.utils.WebResult;
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

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/23 11:06
 */
@Controller
@RequestMapping("/client/user")
public class ClientCartController {
    private static final Logger logger = LoggerFactory.getLogger(ClientCartController.class);

    @Autowired
    private FeignClientService clientService;
    @Autowired
    private FeignCacheService cacheService;


    /**
     * 添加购物车
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/insertCart")
    public Object insertCart(HttpServletRequest request, @RequestBody String jsonBody) {
        try {
            logger.info("-------------添加购物车-------------");
            JSONObject params = JSON.parseObject(jsonBody);
            params.put("userId", getUserId(request));
            return clientService.insertCart(params.toJSONString());
        } catch (Exception e) {
            logger.info("添加购物车失败:{}", e.getMessage());
            return WebResult.error("添加购物车失败");
        }
    }

    /**
     * 删除购物车物品
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/deleteCart")
    public Object deleteCart(@RequestParam Integer id) {
        try {
            logger.info("-------------删除购物车物品-------------");
            return clientService.deleteCart(id);
        } catch (Exception e) {
            logger.info("删除购物车物品失败:{}", e.getMessage());
            return WebResult.error("删除购物车物品失败");
        }
    }

    /**
     * 获取购物车物品
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/cartList")
    public Object cartList(HttpServletRequest request) {
        try {
            logger.info("-------------获取购物车物品-------------");
            return clientService.userCartList(getUserId(request));
        } catch (Exception e) {
            logger.info("获取购物车物品失败:{}", e.getMessage());
            return WebResult.error("获取购物车物品失败");
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
