package com.myyd.config.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMqConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

}
