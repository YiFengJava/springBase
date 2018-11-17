package spring.study.securitydemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspact {

    @Around("execution(* spring.study.securitydemo.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint  pjp) throws  Throwable{
        System.out.println("time asepct start");
        Object[] args = pjp.getArgs();
        for (Object ob :args){
            System.out.println("arg:"+ob);
        }
        Long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        System.out.println("time interceptor耗时："+(System.currentTimeMillis()-startTime));
        System.out.println("time asepct end");
        return  proceed;
    }
}
