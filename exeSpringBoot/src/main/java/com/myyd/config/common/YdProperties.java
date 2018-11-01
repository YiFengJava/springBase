package com.myyd.config.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = YdProperties.PREFIX)
public class YdProperties {
    public static  final  String PREFIX="yd";
    private String name;
    private String sex;
    private String age;

    @PostConstruct
    public void jkjdflkj(){
        System.out.println("+++++++++++++++++++++++++++++++"+name);
    }
}
