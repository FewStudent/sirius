package club.laky.sirius.client.controller;

import club.laky.sirius.client.entity.SysUser;
import club.laky.sirius.client.feign.FeignCacheService;
import club.laky.sirius.client.feign.FeignClientService;
import club.laky.sirius.client.utils.WebResult;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
