package xyz.yudong520.eurekaServerConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaServerConsumer1 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerConsumer1.class, args);
    }
}
