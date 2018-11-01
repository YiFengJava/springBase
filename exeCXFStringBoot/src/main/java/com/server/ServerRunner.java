package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.server")
public class ServerRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServerRunner.class, args);
    }
}
