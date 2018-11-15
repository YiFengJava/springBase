package com.springAware;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main{
//    DispatcherServlet
    public static void main(String[] args) {
//        BeanFactory
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService bean = context.getBean(AwareService.class);
        bean.outputResult();
    }
}
