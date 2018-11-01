package com.aopDome;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AopCofig.class);
        AopMethodService bean = context.getBean(AopMethodService.class);
        bean.add();
        context.close();
    }
}
