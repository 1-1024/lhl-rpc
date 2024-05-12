package com.lhl.example.provider;

import com.lhl.example.common.service.UserService;
import com.lhl.example.rpc.registry.LocalRegistry;
import com.lhl.example.rpc.server.HttpServer;
import com.lhl.example.rpc.server.VertxHttpServer;

/**
 * @Description 简易服务提供者示例
 * @Author hl133
 * @Date 2024/5/11-15:29
 * @Version 1.0
 */
public class EasyProviderExample {

    public static void main(String[] args) {

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 Web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
