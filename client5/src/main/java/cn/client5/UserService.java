package cn.client5;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="user")
public interface UserService {
  @RequestMapping("/name")
  String name(@RequestHeader(value = "X-User-Id") long userId);
}
