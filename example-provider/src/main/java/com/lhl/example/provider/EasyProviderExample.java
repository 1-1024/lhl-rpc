package com.lhl.example.provider;

import com.lhl.example.common.service.UserService;
import com.lhl.example.rpc.core.RpcApplication;
import com.lhl.example.rpc.registry.LocalRegistry;
import com.lhl.example.rpc.server.HttpServer;
import com.lhl.example.rpc.server.VertxHttpServer;

import java.util.Properties;

/**
 * @Description 简易服务提供者示例
 * @Author hl133
 * @Date 2024/5/11-15:29
 * @Version 1.0
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 Web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
