package cn.zu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuApplication.class, args);
	}

	@Bean
	public PatternServiceRouteMapper routeMapper() {
		return new PatternServiceRouteMapper("(^microservice)-(?<name>.+)", "${name}");
	}

}
