package xyz.yudong520.manageadmin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(value = "/test")
    public String get(){
        return  "index.html";
    }
}
