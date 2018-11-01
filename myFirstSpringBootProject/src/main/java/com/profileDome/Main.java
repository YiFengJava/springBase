package com.profileDome;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext() ;
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfig.class);
        context.refresh();
        DemoBean bean = context.getBean(DemoBean.class);
        System.out.println("args = [" + bean.getContent() + "]");
        context.close();
    }
}
