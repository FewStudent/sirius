package club.laky.sirius.resources;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 10044
 */
@MapperScan("club.laky.sirius.resources.dao")
@EnableEurekaClient
@SpringBootApplication
public class SiriusSrApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusSrApplication.class, args);
    }

}
