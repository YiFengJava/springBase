package com.aopDome;

import java.lang.annotation.*;

@Target({ElementType.METHOD})  //注解在方法上的
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAction {
    String name() default "";
}
