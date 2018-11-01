package com.myyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotErrController {

    @GetMapping(value = "/404")
    public String to404Page(){
        return  "/404.html";
    }

    @GetMapping(value = "/index")
    public String toIndexPage(){
        return  "/index/index.html";
    }
}
