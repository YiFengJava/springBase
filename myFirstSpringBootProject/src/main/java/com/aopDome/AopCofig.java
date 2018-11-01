package com.aopDome;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.aopDome")
@EnableAspectJAutoProxy //1   开启spring对AspectJ代理的支持
public class AopCofig {
//
//    aop切面
//    1，添加依赖
//            <!--对aop的支持-->
//        <dependency>
//            <groupId>org.springframework</groupId>
//            <artifactId>spring-aop</artifactId>
//            <version>4.3.14.RELEASE</version>
//        </dependency>
//
//        <!--对AspectJ的支持 -->
//        <dependency>
//            <groupId>org.aspectj</groupId>
//            <artifactId>aspectjrt</artifactId>
//            <version>1.8.10</version>
//        </dependency>
//        <dependency>
//            <groupId>org.aspectj</groupId>
//            <artifactId>aspectjweaver</artifactId>
//            <version>1.8.10</version>
//        </dependency>
//
//    2,编写拦截注解
//     我写的是方法拦截注解MyAction
//      这个注解本身是没有功能的，
//
//    3，写被拦截的类
//         1，注解拦截类AopAnnotationService
//         2，写方法拦截类AopMethodService
//
//    4，写切面类ActionAspect
//
//    5,写个配置类AopCofig
//
//    6测试main Main

}
