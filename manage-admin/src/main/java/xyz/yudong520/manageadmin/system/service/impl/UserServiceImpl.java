package xyz.yudong520.manageadmin.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import xyz.yudong520.manageadmin.core.security.exception.MobileNotFoundException;
import xyz.yudong520.manageadmin.system.dao.UserDao;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.entity.Role;
import xyz.yudong520.manageadmin.system.entity.User;
import xyz.yudong520.manageadmin.system.service.AuthorityService;
import xyz.yudong520.manageadmin.system.service.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private  AuthorityService authorityService;

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
//        Example<User> example = Example.of(user);
//        Optional<User> one = userDao.findOne(example);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact());
//                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
//                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
//                .withIgnorePaths("password");//忽略字段，即不管password是什么值都不加入查询条件
        Example<User> example = Example.of(user ,matcher);
        Optional<User> one = userDao.findOne(example);
        User user1 = one.get();
        Set<Role> roleSet = user1.getRoleSet();
        Set<String> rids=new HashSet<>();
        roleSet.forEach((Role r)->
        rids.add(r.getId())
        );
//        roleSet.size()
        Set<Permissions> permisseionsTableByRoles = authorityService.getPermisseionsTableByRoles(rids);
        user1.setAuthorities(permisseionsTableByRoles);
        return user1;
    }

    @Override
    public User loadUserByMobile(String mobile) {
        User user = new User();
        user.setMobile(mobile);
//        Example<User> example = Example.of(user);
//        Optional<User> one = userDao.findOne(example);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("mobile", ExampleMatcher.GenericPropertyMatchers.exact());
//                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
//                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
//                .withIgnorePaths("password");//忽略字段，即不管password是什么值都不加入查询条件
        Example<User> example = Example.of(user ,matcher);
        List<User> users = userDao.findAll(example);
        if(users==null){
            throw new MobileNotFoundException("用户不存在");
        }
        User user1 = users.get(0);
        return user1;
    }
}
