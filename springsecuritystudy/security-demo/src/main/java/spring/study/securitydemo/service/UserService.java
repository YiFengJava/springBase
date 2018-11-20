package spring.study.securitydemo.service;


import spring.study.securitydemo.entity.User;

public interface UserService {

    User getUserDetailsInfoByName(String name);
    User getUserDetailsInfoByUserId(String userId);
}
