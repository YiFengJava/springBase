package xyz.yudong520.manageadmin.system.service;

import xyz.yudong520.manageadmin.system.entity.User;

public interface UserService {

    User getUserByUsername(String username);

    User loadUserByMobile(String mobile);
}
