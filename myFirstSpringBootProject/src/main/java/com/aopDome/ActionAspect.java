package com.aopDome;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //1   注解声明是一个切面
@Component  //2   注册到spring容器中 让spring管理bean
public class ActionAspect {

    @Pointcut("@annotation(com.aopDome.MyAction)")  //3 声明一个切点
    public void annotationPointCut(){

    }
    @After("annotationPointCut()")  //4  声明一个建言 使用@Pointctu定义的切点
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyAction annotation = method.getAnnotation(MyAction.class);
        System.out.println("method = [" +   method.getName() + "]");
        System.out.println("joinPoint = [" + annotation.name() + "]"); //5  通过反射获取注解上的属性，然后做一下记录之类的操作
    }

    @Before("execution( * com.aopDome.AopMethodService.*(..))")    //6注解声明一个建言，把拦截规则当参数传入
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("method = [" +   method.getName() + "]");
    }



}
