package xyz.yudong520.manageadmin.core.security.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.system.entity.User;
import xyz.yudong520.manageadmin.system.service.UserService;

/**
 * security提供的
 * UserDetailsService  查询用户详情的接口
 * SocialUserDetailsService  查询socialuser的详情接口
 */
//@Component
public class SecurityService implements UserDetailsService,SocialUserDetailsService {

    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(SecurityService.class);
    //复写根据用户名查询用户详情的方法
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("正在登陆的用户是============》"+username);
        User userByUsername = userService.getUserByUsername(username);
        return userByUsername;
    }

    @Override
    public User loadUserByUserId(String userId) throws UsernameNotFoundException {
        return null;
    }
}
