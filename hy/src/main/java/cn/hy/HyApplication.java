package cn.hy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HyApplication {

  public static void main(String[] args) {
    SpringApplication.run(HyApplication.class, args);
  }
}
