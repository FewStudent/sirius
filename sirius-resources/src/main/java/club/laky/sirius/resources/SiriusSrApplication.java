package club.laky.sirius.resources;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 10044
 */
@EnableFeignClients
@MapperScan("club.laky.sirius.resources.dao")
@EnableEurekaClient
@SpringBootApplication
public class SiriusSrApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusSrApplication.class, args);
    }

}
