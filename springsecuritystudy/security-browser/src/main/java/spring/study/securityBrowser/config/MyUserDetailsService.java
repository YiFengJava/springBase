package spring.study.securityBrowser.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import spring.study.securityBrowser.service.UserService;

/**
 * 自定义数据查询服务 需实现 UserDetailsService接口
 */
@Component
public class MyUserDetailsService implements UserDetailsService { //查询用户信息

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    //出来自己数据库中的user表
    @Autowired
    private UserService userService;

    //密码加密器
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override   //UserDetails 用户详情接口
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登陆的用户是：" + username);
        //根据用户名查找用户详细信息
        spring.study.securityBrowser.entity.User userTb = userService.getUserDetailsInfoByName(username);

        User user = new User(username,
                passwordEncoder.encode(userTb.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        org.springframework.security.core.userdetails.User admin =
//                new org.springframework.security.core.userdetails.User(username
//                        , encodedPassword
//                        , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
