package com.myyd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myyd.entity.User;
import com.myyd.dao.UserMapper;
import com.myyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yudong123
 * @since 2018-10-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return   userMapper.selectList(null);
    }
}
