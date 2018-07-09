package cn.acclient;

import cn.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class AcClientApplication {
    @Autowired
    HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(AcClientApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }
}
