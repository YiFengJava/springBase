package com.SpringAop;

import com.springAware.AwareConfig;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAopConfing.class);
        Person bean = (Person)context.getBean("proxyFactoryBean");
        bean.sleep();
        context.close();
    }
}
