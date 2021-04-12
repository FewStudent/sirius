package club.laky.sirius.client.config;

import club.laky.sirius.client.entity.SysUser;
import club.laky.sirius.client.feign.FeignCacheService;
import club.laky.sirius.client.utils.PermissionUtils;
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
import java.util.Map;

public class PermissionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Autowired
    private FeignCacheService cacheService;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        PathMatcher matcher = new AntPathMatcher();
        logger.info("请求的URI：{}", uri);
        if ("/client/page/login".equals(uri) || "/error".equals(uri)
                || matcher.matchStart("/client/common/**", uri)
                || matcher.matchStart("/client/page/common/**", uri)) {
            logger.info("无需鉴权的放行请求：{}", uri);
            return true;
        }
        if (matcher.matchStart("/static/**", uri)) {
            logger.info("静态资源放行：{}", uri);
            return true;
        }
        String isApi = request.getHeader("isApi");
        if (!StringUtils.isEmpty(isApi) && isApi.equals("Y")) {
            logger.info("获得网关许可直接放行：{}", uri);
            return true;
        }
        //页面跳转 从参数中获取token
        String token;
        if (matcher.matchStart("/client/page/**", uri)) {
            token = request.getParameter("token");
        } else {
            token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter("token");
            }
        }
        if (StringUtils.isEmpty(token)) {
            logger.error("没有token无法进行访问,请前往登录");
            response.sendRedirect(request.getContextPath() + "/client/page/login");
            return false;
        }
        Map<String, Object> result = (Map<String, Object>) cacheService.get(token);
        logger.info("获取到token：{}的数据：{}", token, result.toString());
        if (result.get("status").equals("true")) {
            SysUser user = JSON.parseObject((String) result.get("data"), SysUser.class);
            logger.info("获取到用户：{}的信息", user.getAccount());
            if (PermissionUtils.hasPermission(user, uri)) {
                logger.info("token:{}用户拥有该访问权限", token);
                return true;
            } else {
                logger.error("没有该访问权限：{},重定向到首页", uri);
                response.sendRedirect(request.getContextPath() + "/client/page/index");
                /*response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().write("没有该权限");*/
                return false;
            }
        } else {
            logger.error("token令牌{}已过期,重定向到登录页面", token);
            response.sendRedirect(request.getContextPath() + "/client/page/login");
            return false;
        }
    }
}
