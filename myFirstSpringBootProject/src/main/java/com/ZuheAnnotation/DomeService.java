package com.ZuheAnnotation;

import org.springframework.stereotype.Service;

@Service
public class DomeService {

    public void outputResult(){
        System.out.println("从组合注解中照样获取bean");
    }
}
