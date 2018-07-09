package cn.client5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
public class Client5Application implements ApplicationRunner {

	@Autowired
	HelloService helloService;

	@Autowired
	UserService userService;

	@Autowired
	DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadBalancer;

//	@Autowired
//	private EurekaDiscoveryClient eurekaDiscoveryClient;

	private Logger log = LoggerFactory.getLogger(Client5Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Client5Application.class, args);
		System.exit(0);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) {
		System.out.println(userService.name(10000000514L));
		System.out.println(helloService.hi("Client5"));
	}

	public void run_2(ApplicationArguments applicationArguments)  {

		ServiceInstance instancex = loadBalancer.choose("ec");
		if( instancex != null ) {
			String result = String.format("load balancer choose ec: http://%s:%s", instancex.getHost(),
				instancex.getPort());
			System.out.println(result);
			log.info(result);
		}

//		System.out.println(eurekaDiscoveryClient.getServices());

	  try {
			System.out.println("discovery client get services return:");
			System.out.println(discoveryClient.getServices());
			System.out.println(discoveryClient.getInstances("ec"));
			for(ServiceInstance instance : discoveryClient.getInstances("ec")) {
				for(Map.Entry kv : instance.getMetadata().entrySet()) {
					System.out.println(kv.getKey() + ":" + kv.getValue());
				}
				System.out.println(instance.getHost() + "," + instance.getPort() + "," + instance.getUri
						() + "," + instance.getServiceId());
			}

			long begin = System.currentTimeMillis();
			String times = System.getProperty("times", "1");
			for(int i=0;i<Integer.parseInt(times);i++) {
				String result = helloService.hi("jack");
				long timer = System.currentTimeMillis();
				System.out.println(result + " spent(ms): " + (timer - begin));
				begin = timer;
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
