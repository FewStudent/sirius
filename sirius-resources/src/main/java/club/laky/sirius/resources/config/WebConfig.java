package club.laky.sirius.resources.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.UUID;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    @Value(value = "${system.imgUpLoadPath}")
    private String imgUpLoadPath;


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        logger.info("---------------配置跨域---------------");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedMethods("*")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "OPTIONS","PUT")
                        .maxAge(3600);
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        isExist(imgUpLoadPath);//创建文件夹
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("file:" + imgUpLoadPath);
    }

    /**
     * 判断文件夹是否存在,不存在则创建文件夹
     */
    private void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
