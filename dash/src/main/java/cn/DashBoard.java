package cn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashBoard {

  @RequestMapping("/hi")
  public String hi() {
    return "index.jsp";
  }

}
