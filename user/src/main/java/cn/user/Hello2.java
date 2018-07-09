package cn.user;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Hello2 {

  private RestTemplate restTemplate = new RestTemplate();

  @HystrixCommand(fallbackMethod = "hi2Fallback")
  public String hi2() {
    return restTemplate.getForEntity("http://localhost:8182/hi?name=hi2", String.class).getBody();
  }

  public String hi2Fallback(Throwable exp) {
    return "hi2 fallback: " + exp.getMessage();
  }

}
