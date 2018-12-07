package spring.study.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import spring.study.securitydemo.dao.UserDao;
import spring.study.securitydemo.entity.User;

@Component
public class MyConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserDao userDao;

    @Override
    public String execute(Connection<?> connection) {
        //根据默认创建用户并返回唯一标识

        String userId = System.currentTimeMillis() + "";
        User user=new User();
        user.setUserId(userId);
        user.setUsername(connection.getDisplayName());
        user.setNickName(connection.getDisplayName());
        user.setPassword("123456");
        User save = userDao.save(user);
        return save.getUserId();
    }
}
