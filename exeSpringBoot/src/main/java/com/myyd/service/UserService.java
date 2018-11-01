package com.myyd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myyd.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yudong123
 * @since 2018-10-19
 */
public interface UserService extends IService<User> {


    List<User> getUserList();
}
