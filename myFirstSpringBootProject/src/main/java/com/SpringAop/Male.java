package com.SpringAop;

import org.springframework.stereotype.Component;

@Component
public class Male implements  Person {
    @Override
    public void sleep() {
        System.out.println("男人睡觉 =============");
    }
}
