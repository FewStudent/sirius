package club.laky.sirius.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SiriusRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiriusRedisApplication.class, args);
	}

}
