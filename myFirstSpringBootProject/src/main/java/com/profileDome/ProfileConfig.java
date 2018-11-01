package com.profileDome;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {
    @Bean
    @Profile("dev")  //
    public DemoBean devDemeBean(){
        return new DemoBean("from devrlopment profile");
    }

    @Bean
    @Profile("prod")
    public DemoBean prodDemoBean(){
        return  new DemoBean("from production profile");
    }
}
