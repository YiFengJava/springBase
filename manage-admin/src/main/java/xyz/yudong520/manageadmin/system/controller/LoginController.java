package xyz.yudong520.manageadmin.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.yudong520.manageadmin.core.security.support.SimpleResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

//    private Logger logger =LoggerFactory.getLogger(LoginController.class);

    //请求的缓存  security中的请求缓存工具类
    private RequestCache requestCache=new HttpSessionRequestCache();

    //请求转发的处理工具
    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();


    //跳转登陆页面
    @RequestMapping(value = "/page")
    public String loginPage(){
        return  "login.html";
    }

    @GetMapping(value = "/logoutsuccess")
    private String logoutsuccess(){
        log.info("退出成功重定向到登陆页面");
        return  "redirect:login/page";
    }

//    //跳转退出登陆页面
//    @RequestMapping(value = "/logout")
//    public String logoutPage(Model model){
//        return  "logout.html";
//    }

    //自定义登陆认证的接口
    @RequestMapping(value = "/auth")
    @ResponseBody
    @ResponseStatus(value =HttpStatus.UNAUTHORIZED )
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从缓存中得到请求的缓存
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null ){
            //得到请求的地址
            String targetUrl=savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是："+targetUrl);
            redirectStrategy.sendRedirect(request,response,"/login/page");
//            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
//                //直接跳转到配置的登陆页面
//                redirectStrategy.sendRedirect(request,response,"/login");
//            }
        }
        return  new SimpleResponse(HttpStatus.UNAUTHORIZED.value(),"请先认证",null);
    }






}
