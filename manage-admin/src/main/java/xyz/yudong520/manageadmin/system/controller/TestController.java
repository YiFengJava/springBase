package xyz.yudong520.manageadmin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.yudong520.manageadmin.core.security.authorized.Authorized;
import xyz.yudong520.manageadmin.system.entity.User;
import xyz.yudong520.manageadmin.system.service.AuthorityService;

import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping(value = "/aa")
    public String aa(){
        return  "aa.html";
    }


    @Autowired
    FindByIndexNameSessionRepository sessionRepository;

    @GetMapping("/test/findByUsername")
    @ResponseBody
    public Map findByUsername(@RequestParam String username) {
        Map usersSessions = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
        return usersSessions;
    }

    @GetMapping(value = "/test/ecach")
    @ResponseBody
    public String testg(String username){
        return  authorityService.testEcach(username);
    }


    @GetMapping(value = "/thtest")
    @ResponseBody
    @Authorized(value = "测试权限")
    public Object jklsjdf(){
        return "测试";
    }

    @GetMapping(value = "/test/ecach1")
    @ResponseBody
    public User jdsklfj(String username){
        return authorityService.getTokenByGsid(username);
    }

    @GetMapping(value = "/page1")
    public String  page1(){
        return  "login2.html";
    }

    @GetMapping(value ="/page2" )
    public String  page2(){
        return  "register.html";
    }



}
