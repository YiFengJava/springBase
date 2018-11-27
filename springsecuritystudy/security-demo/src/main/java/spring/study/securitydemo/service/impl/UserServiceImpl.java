package spring.study.securitydemo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.study.securitydemo.dao.UserDao;
import spring.study.securitydemo.entity.User;
import spring.study.securitydemo.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User getUserDetailsInfoByName(String name) {
        User user = userDao.getUserByUsername(name);
//        User user =new User();
        String password = user.getPassword();
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
        return  user;
    }

    @Override
    public User getUserDetailsInfoByUserId(String userId) {
        User user = userDao.getUserByUserId(userId);
        return user;
    }
}
