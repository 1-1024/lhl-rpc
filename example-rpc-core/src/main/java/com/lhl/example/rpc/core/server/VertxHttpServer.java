package com.lhl.example.rpc.core.server;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 监听指定端口处理请求
 * @Author hl133
 * @Date 2024/5/11-17:37
 * @Version 1.0
 */
public class VertxHttpServer implements HttpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(VertxHttpServer.class);

    @Override
    public void doStart(int port) {
        // 实例化
        Vertx vertx = Vertx.vertx();

        // 创建Http服务器
        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();

        // 绑定请求处理器
        httpServer.requestHandler(new HttpServiceHandle());

        // 启动 Http 服务器并监听指定端口
        httpServer.listen(port, result -> {
           if(result.succeeded()) {
               LOGGER.info("Server is now listening on port : {}", port);
           } else {
               LOGGER.error("Failed to start server : ", result.cause());
           }
        });

    }
}
