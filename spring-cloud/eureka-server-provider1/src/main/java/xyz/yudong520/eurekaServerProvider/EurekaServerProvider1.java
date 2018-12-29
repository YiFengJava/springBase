package xyz.yudong520.eurekaServerProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaServerProvider1 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerProvider1.class, args);
    }
}
