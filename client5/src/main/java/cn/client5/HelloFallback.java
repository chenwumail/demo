package cn.client5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloFallback implements HelloService {
  private Logger log = LoggerFactory.getLogger(HelloFallback.class);
  @Override
  public String hi(String name) {
    log.error("request failed, maybe service not found or timeout.");
    return "request failed, maybe service not found or timeout.";
  }
}
