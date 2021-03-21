package club.laky.sirius.gateway.filter;

import club.laky.sirius.gateway.constant.TokenConstant;
import club.laky.sirius.gateway.feign.FeignCacheService;
import club.laky.sirius.gateway.utils.WebResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prl
 * @Desrcription:
 * @date 2021/3/20 13:58
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    //放行请求
    private static final List<String> UN_AUTH_URI = new ArrayList<>();

    private static final List<String> ALLOW_URI = new ArrayList<>();

    @Autowired
    private FeignCacheService cacheService;

    static {
        UN_AUTH_URI.add("/admin-service/api/**");
        UN_AUTH_URI.add("/client-service/api/**");

        ALLOW_URI.add("/admin-service/**");
        ALLOW_URI.add("/client-service/**");
    }


    /**
     * 放行：
     * /admin-service/api/**
     * /client-service/api/**
     * 允许访问：
     * /admin-service/**
     * /client-service/**
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        String ip = exchange.getRequest().getRemoteAddress().toString();

        List<String> token = exchange.getRequest().getHeaders().get("token");
        logger.info("------------------------------------------------------");
        logger.info("请求参数token:{}", token == null? null : token.get(0));
        logger.info("请求的URI:{}", path);
        logger.info("请求的地址IP:{}", ip);
        logger.info("------------------------------------------------------");


        PathMatcher matcher = new AntPathMatcher();
        //检测放行请求
        for (String auth_uri : UN_AUTH_URI) {
            if (matcher.matchStart(auth_uri, path)) {
                logger.info("好家伙");
                return chain.filter(exchange); //继续向下执行
            }
        }


        //检测鉴权请求
        for (String allow_uri : ALLOW_URI) {
            logger.info("地址匹配结果：{}", matcher.matchStart(path, allow_uri));
            if (matcher.matchStart(allow_uri,path)) {
                if (null == token) {
                    return unauthorized(exchange);
                } else {
                    String tokenCache = (String) cacheService.get(token.get(0));
                    //获取token失败,token过期
                    if (StringUtils.isEmpty(tokenCache)) {
                        return expire(exchange);
                    }
                    //TODO

                }

            }
        }


        return unauthorized(exchange);
    }

    private static Mono<Void> expire(ServerWebExchange exchange) {
        return Mono.defer(() -> {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//设置status
            final ServerHttpResponse response = exchange.getResponse();
            byte[] bytes = WebResult.expire().toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            logger.info("token已过期,去前往登录验证");
            return response.writeWith(Flux.just(buffer));//设置body
        });
    }

    private static Mono<Void> unauthorized(ServerWebExchange exchange) {
        return Mono.defer(() -> {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//设置status
            final ServerHttpResponse response = exchange.getResponse();
            byte[] bytes = WebResult.unAuth().toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            logger.info("未检测到用户的token,去前往登录验证");
            return response.writeWith(Flux.just(buffer));//设置body
        });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
