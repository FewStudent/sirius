package club.laky.sirius.admin.interceptor;

import club.laky.sirius.admin.entity.SysUser;
import club.laky.sirius.admin.feign.FeignCacheService;
import club.laky.sirius.admin.utils.PermissionUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);

    private static List<String> allow_path = new ArrayList<>();

    private static final String PAGE_PATH = "/admin/page/**";
    static{
        allow_path.add("/admin/page/login");
        allow_path.add("/error");
        allow_path.add("/admin/admin/login");
        allow_path.add("/static/**");


    }

    @Autowired
    private FeignCacheService cacheService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        PathMatcher matcher = new AntPathMatcher();
        for (String s : allow_path) {
            if (matcher.matchStart(s, uri)) {
                logger.info("无需鉴权的放行请求：{}", uri);
                return true;
            }
        }
        String isApi = request.getHeader("isApi");
        if (!StringUtils.isEmpty(isApi) && "Y".equals(isApi)) {
            logger.info("获得网关许可直接放行：{}", uri);
            return true;
        }
        //页面跳转 从参数中获取token
        String token;
        if (matcher.matchStart(PAGE_PATH, uri)) {
            token = request.getParameter("token");
        } else {
            token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter("token");
            }
        }
        if (StringUtils.isEmpty(token)) {
            logger.error("没有token无法进行访问,请前往登录");
            response.sendRedirect(request.getContextPath() + "/admin/page/login");
            return false;
        }
        Map<String, Object> result = (Map<String, Object>) cacheService.get(token);
        logger.info("获取到token：{}的数据：{}", token, result.toString());
        if ("true".equals(result.get("status"))) {
            SysUser user = JSON.parseObject((String) result.get("data"), SysUser.class);
            if(user == null){
                logger.error("用户token已过期");
                response.sendRedirect(request.getContextPath() + "/admin/page/login");
                return false;
            }
            logger.info("获取到用户：{}的信息", user.getAccount());
            if (PermissionUtils.hasPermission(user, uri)) {
                logger.info("token:{}用户拥有该访问权限", token);
                return true;
            } else {
                logger.error("没有该访问权限：{},重定向到首页", uri);
                response.sendRedirect(request.getContextPath() + "/admin/page/index");
                return false;
            }
        } else {
            logger.error("token令牌{}已过期,重定向到登录页面", token);
            response.sendRedirect(request.getContextPath() + "/admin/page/login");
            return false;
        }
    }


}
