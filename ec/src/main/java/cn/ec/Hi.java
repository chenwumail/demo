package cn.ec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hi {
  @Autowired
  private Hello3 hello2;

  @RequestMapping("/hi")
  public String hi(@RequestParam("name") String name) {
    return "Hi " + name + "," + System.getProperty("server.port") + "!";
  }

  @RequestMapping("/api/hi")
  public String hi_v2(@RequestParam("name") String name) {
    return "Hi " + name + "," + System.getProperty("server.port") + "!";
  }

  @RequestMapping("/api/hi3")
  public String hi_v3() {
    return hello2.hi3();
  }

  @RequestMapping("/api/hi4")
  public String hi_v4() {
    return hello2.hi4();
  }
}
