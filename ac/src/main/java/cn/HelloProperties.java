package cn;

import lombok.*;
import org.springframework.boot.context.properties.*;

@Data
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String message = "jack";
    private boolean show = true;
}
