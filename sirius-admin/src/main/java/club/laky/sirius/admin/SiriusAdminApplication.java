package club.laky.sirius.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients({"club.laky.sirius.admin.feign"})
@MapperScan("club.laky.sirius.admin.dao")
@SpringBootApplication
public class SiriusAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusAdminApplication.class, args);
    }

}
