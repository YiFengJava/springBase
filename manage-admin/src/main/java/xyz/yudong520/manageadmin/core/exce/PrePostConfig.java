package xyz.yudong520.manageadmin.core.exce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("com.InitDestoryBeanDome")
public class PrePostConfig {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public BeanWayService beanWayService(){
        return  new BeanWayService();
    }

    @Bean
    public JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }
}
