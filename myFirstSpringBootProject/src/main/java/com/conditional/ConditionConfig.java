package com.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService WindowsListService(){
        return  new WindowsListServcie();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService LinuxListService(){
        return  new LinuxListService();
    }
}
