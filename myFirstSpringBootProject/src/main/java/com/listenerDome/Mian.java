package com.listenerDome;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(EventConfig.class);
        DomePublisher bean = context.getBean(DomePublisher.class);
        String msg = "kjsldkfjalkjfkldsjfl;ajdsfl;asdkjflkads";
        bean.publish(msg);
        context.close();
    }
}
