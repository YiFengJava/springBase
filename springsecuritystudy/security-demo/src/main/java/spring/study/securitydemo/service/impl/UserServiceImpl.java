package spring.study.securitydemo.service.impl;


import org.springframework.stereotype.Service;
import spring.study.securitydemo.entity.User;
import spring.study.securitydemo.service.UserService;


@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Override
    public User getUserDetailsInfoByName(String name) {
        return new User();
    }

    @Override
    public User getUserDetailsInfoByUserId(String userId) {

        return new User();
    }
}
