package com.ZuheAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                =new AnnotationConfigApplicationContext(DomeConfig.class);
        DomeService bean = context.getBean(DomeService.class);
        bean.outputResult();
        context.close();
    }
}
