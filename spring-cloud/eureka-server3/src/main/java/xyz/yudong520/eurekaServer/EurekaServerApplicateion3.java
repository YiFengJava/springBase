package xyz.yudong520.eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplicateion3 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplicateion3.class, args);
    }
}
