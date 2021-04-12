package club.laky.sirius.client.utils;

import club.laky.sirius.client.entity.SysUser;
import club.laky.sirius.client.feign.FeignCacheService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ModelUtils {

    @Autowired
    private FeignCacheService cacheService;

    public ModelAndView createModel(HttpServletRequest request, String view) {
        ModelAndView result = new ModelAndView();
        result.setViewName(view);
        //获取token与缓存
        String token = request.getParameter("token");
        Map<String, Object> data = (Map<String, Object>) cacheService.get(token);

        //用户存在则添加进Model
        SysUser user = JSON.parseObject((String) data.get("data"), SysUser.class);
        if (user != null) {
            result.addObject("client", user);
        }
        return result;
    }
}
