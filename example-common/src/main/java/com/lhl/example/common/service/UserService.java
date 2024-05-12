package com.lhl.example.common.service;

import com.lhl.example.common.model.User;

/**
 * @Description 用户服务，实现序列化接口，为后续网络传输序列化提供支持
 * @Author hl133
 * @Date 2024/5/11-14:44
 * @Version 1.0
 */
public interface UserService {
    /*
     * @Author hl133
     * @Description // 获取用户
     * @Date 14:52 2024/5/11
     * @Param user: 用户
     * @return: com.lhl.model.User
     **/
    User getUser(User user);
}
