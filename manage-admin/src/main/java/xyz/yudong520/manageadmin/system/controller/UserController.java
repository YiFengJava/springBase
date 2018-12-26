package xyz.yudong520.manageadmin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final String PAGE_PREFIX_USER="system/user";

//    @Autowired
//    private SessionStrategy sessionRegistry;

    @GetMapping()
    public String userManagerPage(){
        return  PAGE_PREFIX_USER+"/user.html";
    }

//
//    @GetMapping("/sessions/")
//    public String sessions(Authentication authentication, Model model){
//        List<SessionInformation> list=sessionRegistry.getAllSessions(authentication.getPrincipal(),false);
//    }
}
