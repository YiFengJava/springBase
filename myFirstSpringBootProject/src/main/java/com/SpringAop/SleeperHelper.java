package com.SpringAop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("SleeperHelper")
public class SleeperHelper implements MethodBeforeAdvice,AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("睡觉前刷牙！");
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("睡觉后关灯！");
    }
}
