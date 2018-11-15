package com.SpringAop;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.SpringAop")
public class SpringAopConfing {

    //注入advice
    @Autowired
    private SleeperHelper sleeperHelper;


    //注入被代理对象
    @Autowired
    private Male male;

    //注册PointCut切点
    @Bean
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut(){
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut=new JdkRegexpMethodPointcut();
        //配置添加连接点（Joinpoint）
        jdkRegexpMethodPointcut.setPattern("package com.SpringAop.*.sleep");
        return  jdkRegexpMethodPointcut;
    }

    //注册Advisor切面 Advisor可以看成是Aspect的一个单元吧
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut());
        defaultPointcutAdvisor.setAdvice(sleeperHelper);
        return  defaultPointcutAdvisor;
    }

    @Bean("proxyFactoryBean")
    public ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean=new ProxyFactoryBean();
        //代理目标
        proxyFactoryBean.setTarget(male);
//         <property name="interceptorNames" value="sleepHelperAdvisor"></property>
//        proxyFactoryBean.setInterceptorNames("defaultPointcutAdvisor");
//        proxyFactoryBean.setOpaque(true);
        //加入通知
        proxyFactoryBean.setInterceptorNames(sleeperHelper.getClass().getSimpleName());
//        proxyFactoryBean.setProxyInterfaces(defaultPointcutAdvisor().getClass().getClasses());
        //被代理接口
        proxyFactoryBean.setProxyInterfaces(Person.class.getInterfaces());
        return  proxyFactoryBean;
    }

}
