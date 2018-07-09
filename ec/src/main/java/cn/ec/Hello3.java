package cn.ec;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Hello3 {

  private RestTemplate restTemplate = new RestTemplate();

  @HystrixCommand(fallbackMethod = "hi3Fallback")
  public String hi3() {
    return restTemplate.getForEntity("http://localhost:8183/hi?name=ec2", String.class).getBody();
  }

  public String hi3Fallback(Throwable exp) {
    return "ec2 fallback: " + exp.getMessage();
  }


  @HystrixCommand(fallbackMethod = "hi4Fallback")
  public String hi4() {
    return restTemplate.getForEntity("http://localhost:8183/hi?name=ec2-4", String.class).getBody();
  }

  public String hi4Fallback(Throwable exp) {
    return "ec2-4 fallback: " + exp.getMessage();
  }
}
