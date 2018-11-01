package com.InitDestoryBeanDome;
import com.javaBeanBaseDome.JavaBeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //AnnotationConfigApplicationContext作为spring的一个容器
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(PrePostConfig.class);
        BeanWayService bean = context.getBean(BeanWayService.class);
//        context.getBean(JSR250WayService.class);
        context.close();
    }
}
