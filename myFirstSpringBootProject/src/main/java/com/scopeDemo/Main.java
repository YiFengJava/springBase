package com.scopeDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        DefaultScopeDome bean1 = context.getBean(DefaultScopeDome.class);
        DefaultScopeDome bean2 = context.getBean(DefaultScopeDome.class);
        OtherScopeDome bean3 = context.getBean(OtherScopeDome.class);
        OtherScopeDome bean4 = context.getBean(OtherScopeDome.class);
        System.out.println(bean1==bean2);
        System.out.println(bean3==bean4);
    }
}
