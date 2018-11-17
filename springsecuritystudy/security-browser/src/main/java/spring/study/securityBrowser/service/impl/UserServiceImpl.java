package spring.study.securityBrowser.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.study.securityBrowser.entity.User;
import spring.study.securityBrowser.service.UserService;


@Service
public class UserServiceImpl implements UserService{

//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Override
    public User getUserDetailsInfoByName(String name) {
        User user = new User();
        user.setUsername(name);
//        String encodedPassword = passwordEncoder.encode("123456");
//        user.setPassword(encodedPassword);
        user.setPassword("123456");
        user.setId("10");
        return user;
    }
}
