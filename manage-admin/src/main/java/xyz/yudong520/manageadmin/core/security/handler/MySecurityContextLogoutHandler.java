package xyz.yudong520.manageadmin.core.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.core.redis.RedisDb;
import xyz.yudong520.manageadmin.core.redis.RedisHandle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MySecurityContextLogoutHandler
        extends SecurityContextLogoutHandler
 {


//    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        Cookie[] cookies = request.getCookies();
        log.info("注销成功！");
//        String id = request.getSession().getId();
//        log.info("sessionid为："+ id);
//        String s = "spring:session:sessions:" + id;
//        log.info("删除redis中key为："+s);
//        RedisDb.delString(s);
//        for (Cookie cookie:cookies){
//            String name = cookie.getName();
//            if(name.equals("JSESSIONID")){
//                log.info("cookie中的JSESSIONID为："+cookie.getValue());
//            }
//        }
        super.logout(request, response, authentication);
    }
}
