package com.lhl.example.rpc.server;

/**
 * @Description HTTP服务器接口
 * @Author hl133
 * @Date 2024/5/11-16:04
 * @Version 1.0
 */
public interface HttpServer {

    /*
     * @Author hl133
     * @Description //  启动服务器
     * @Date 17:36 2024/5/11
     * @Param port: 端口号
     * @return: void
     **/
    void doStart(int port);
}
