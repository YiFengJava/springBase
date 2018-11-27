package spring.study.securitydemo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import spring.study.securitydemo.entity.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController {



    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping()
    public String loginPage(){
        return  "demo-signLogin.html";
    }

//
//    @ApiOperation("注册，绑定")
//    @PostMapping(value = "/regists")
//    public void userRegists(User user, HttpServletRequest request){
//        //不管是注册还是绑定都会有一个用户唯一标识
//        String userId = user.getUserId();
//        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
//    }
}
