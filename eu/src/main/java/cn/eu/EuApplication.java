package cn.eu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EuApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuApplication.class, args);
	}
}
