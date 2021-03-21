package club.laky.sirius.eruka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author lakyjpan
 */
@SpringBootApplication
@EnableEurekaServer
public class SiriusEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusEurekaApplication.class, args);
    }

}
