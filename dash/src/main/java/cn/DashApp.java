package cn;

import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.EurekaClientConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.bootstrap.BootstrapConfiguration;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
//import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.hypermedia.CloudHypermediaAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.commons.util.UtilAutoConfiguration;
import org.springframework.cloud.netflix.archaius.ArchaiusAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration;
import org.springframework.cloud.netflix.eureka.config.EurekaClientConfigServerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.config
  .EurekaDiscoveryClientConfigServiceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.config
  .EurekaDiscoveryClientConfigServiceBootstrapConfiguration;
import org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.eureka.RibbonEurekaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Configuration
@Import({

  // eureka
  CommonsClientAutoConfiguration.class,
//  NoopDiscoveryClientAutoConfiguration.class,
//  CloudHypermediaAutoConfiguration.class,
//  LoadBalancerAutoConfiguration.class,
  UtilAutoConfiguration.class,

  EurekaDiscoveryClientConfiguration.class,
  EurekaClientConfigServerAutoConfiguration.class,
  EurekaDiscoveryClientConfigServiceAutoConfiguration.class,
  EurekaClientAutoConfiguration.class,
  RibbonEurekaAutoConfiguration.class,
//  EurekaDiscoveryClientConfiguration.class,
  EurekaDiscoveryClientConfigServiceBootstrapConfiguration.class,

  // spring web
//  ArchaiusAutoConfiguration.class,
//-  FeignRibbonClientAutoConfiguration.class,
//-  FeignAutoConfiguration.class,
//-  FeignAcceptGzipEncodingAutoConfiguration.class,
//-  FeignContentGzipEncodingAutoConfiguration.class,
//  HystrixAutoConfiguration.class,
//  RibbonAutoConfiguration.class,
 //- RxJavaAutoConfiguration.class,
//	ServoMetricsAutoConfiguration.class,

//  HystrixCircuitBreakerConfiguration.class,

//  DefaultEurekaClientConfig.class,

  DispatcherServletAutoConfiguration.class,
  ServletWebServerFactoryAutoConfiguration.class,
//  EmbeddedServletContainerAutoConfiguration.class,
  ErrorMvcAutoConfiguration.class,
  HttpEncodingAutoConfiguration.class,
  HttpMessageConvertersAutoConfiguration.class,
  JacksonAutoConfiguration.class,
  MultipartAutoConfiguration.class,
  TransactionAutoConfiguration.class,
  ValidationAutoConfiguration.class,
  WebMvcAutoConfiguration.class


})
@ComponentScan
@EnableDiscoveryClient
public class DashApp {
  public static void main(String[] args) {
    SpringApplication.run(DashApp.class, args);
  }
}
