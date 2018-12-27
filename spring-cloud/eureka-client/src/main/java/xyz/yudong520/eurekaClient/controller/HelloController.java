package xyz.yudong520.eurekaClient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {

    @Value("${message: 本地消息}")
    private String message;

    @RequestMapping("/message")
    public String message() {

        return this.message;
    }

}