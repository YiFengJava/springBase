package com.javaBeanBaseDome;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        //AnnotationConfigApplicationContext作为spring的一个容器
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(JavaBeanConfig.class);
        FunctionService bean = context.getBean(FunctionService.class);
        UserFunctionService bean1 = context.getBean(UserFunctionService.class);
        System.out.println(bean.sayHelloWorld());
        System.out.println(bean1.sayholle());
    }
}
