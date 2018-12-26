package xyz.yudong520.manageadmin.core.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MySecurityContextLogoutHandler
        extends SecurityContextLogoutHandler
 {

     @Autowired
     RedisTemplate<Object, Object> redisTemplate;
//     private RedisTemplate redisTemplate;

//    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        log.info("注销成功！");
        String id = request.getSession().getId();
        log.info("sessionid为："+ id);
        String s = "spring:session:sessions:" + id;
        String s1 = "spring:session:sessions:expires:" + id;
        Boolean delete = redisTemplate.delete(s);
        Boolean delete1 = redisTemplate.delete(s1);
        if(delete&&delete1){
            log.info("删除redis中key为："+s);
            log.info("删除redis中key为："+s1);
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            if(name.equals("JSESSIONID")){
                cookie.setMaxAge(-1);
                cookie.setValue(null);
            }
        }
//        //清除cookie：
//        Cookie cookie = new Cookie("JSESSIONID", null);
//        cookie.setMaxAge(-1);
//        response.addCookie(cookie);
        super.logout(request, response, authentication);
    }
}
