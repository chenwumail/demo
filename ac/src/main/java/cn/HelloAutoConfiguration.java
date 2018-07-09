package cn;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(
        prefix = "hello",
        value = "enabled",
        matchIfMissing = true
)
public class HelloAutoConfiguration {
    @Autowired
    private HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        System.out.println("HelloAutoConfiguration::helloService()");
        HelloService h = new HelloService();
        h.setMsg(helloProperties.getMessage());
        h.setShow(helloProperties.isShow());
        return h;
    }
}
