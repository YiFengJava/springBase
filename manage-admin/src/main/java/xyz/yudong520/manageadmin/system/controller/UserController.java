package xyz.yudong520.manageadmin.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final String PAGE_PREFIX_USER="system/user";

    @GetMapping()
    public String userManagerPage(){
        return  PAGE_PREFIX_USER+"/user.html";
    }
}
