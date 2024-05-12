package com.lhl.example.consumer;

import com.lhl.example.common.model.User;
import com.lhl.example.common.service.UserService;
import com.lhl.example.rpc.proxy.ServiceProxyFactory;

/**
 * @Description 简易服务消费者示例
 * @Author hl133
 * @Date 2024/5/11-15:45
 * @Version 1.0
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User("hl313");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }

}
