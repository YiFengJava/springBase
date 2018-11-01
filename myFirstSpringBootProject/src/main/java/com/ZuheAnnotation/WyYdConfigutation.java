package com.ZuheAnnotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration  //1 元注解
@ComponentScan  //2 元注解
public @interface WyYdConfigutation {
    String[] value() default {}; //3 覆盖value参数
}

