package cn.client5;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ec", fallback = HelloFallback.class)
//@FeignClient(name="ec", url="http://localhost:8183/")
public interface HelloService {
  @RequestMapping("/hi")
  String hi(@RequestParam("name") String name);
}
