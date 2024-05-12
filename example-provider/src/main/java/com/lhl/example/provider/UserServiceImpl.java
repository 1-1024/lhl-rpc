package com.lhl.example.provider;

import com.lhl.example.common.model.User;
import com.lhl.example.common.service.UserService;

/**
 * @Description 描述
 * @Author hl133
 * @Date 2024/5/11-15:24
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    public User getUser(User user) {
        System.out.println("用户名: " + user.getName());
        return user;
    }
}
