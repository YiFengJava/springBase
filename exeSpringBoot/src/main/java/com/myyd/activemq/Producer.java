package com.myyd.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


@Component
public class Producer  implements CommandLineRunner{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void run(String... args) throws Exception {
        send("Sampme message");
        System.out.println("Message was sent to the Queue");
    }

    public void send(String message) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
    }
}
