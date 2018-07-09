package cn.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  HelloService helloService;

  @Autowired
  Hello2 hello2;

  @RequestMapping("/name")
  public String name(@RequestHeader(value = "X-User-Id") long userId) {
    return "welcome user " + userId;
  }

  @RequestMapping("/api/me")
  public String me() {
    return "I's user micro-service.";
  }

  @RequestMapping("/api/user/hi")
  public String hi() {
    return "Hi, jack.";
  }

  @RequestMapping("/api/ec_hi")
  public String ec_hi() {
    return helloService.hi("ec");
  }

  @RequestMapping("/api/microservice-user/he")
  public String he() {
    return "He he";
  }

  @RequestMapping("/api/hi2")
  public String hi2() {
    return hello2.hi2();
  }
}
