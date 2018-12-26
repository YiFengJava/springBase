package xyz.yudong520.manageadmin.system.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.yudong520.manageadmin.system.entity.Role;
import xyz.yudong520.manageadmin.system.entity.User;

import java.util.Set;

public class BaseController {

    User getCurrentUser(){
        //获取该管理用户的授权信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //在授权信息中获取用户个人基本信息
        User user = (User) authentication.getPrincipal();
        return  user;
    }


//    Object getOther(){
//        User currentUser = getCurrentUser();
//        Set<Role> roleSet = currentUser.getRoleSet();
//
//    }
}
