package com.listenerDome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DomePublisher {

    @Autowired
    ApplicationContext applicationContext;  //用来发布事件

    public void publish(String msg){
        applicationContext.publishEvent(new DomeEvent(this,msg));  //使用该方法发布事件
    }
}
