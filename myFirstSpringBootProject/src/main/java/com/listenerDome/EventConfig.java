package com.listenerDome;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//配置注解
@Configuration
//扫面包
@ComponentScan("com.listenerDome")
public class EventConfig {
    //自定义事件并监听，并发布除了事件
}
