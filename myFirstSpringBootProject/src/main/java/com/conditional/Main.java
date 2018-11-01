package com.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService bean = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name"));
        System.out.println(bean.showListCmd());
        context.close();
    }
}
