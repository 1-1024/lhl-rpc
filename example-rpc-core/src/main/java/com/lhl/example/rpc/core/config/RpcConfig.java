package com.lhl.example.rpc.core.config;

import com.lhl.example.rpc.core.constant.RpcConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description RPC框架配置
 * @Author hl133
 * @Date 2024/5/31-17:29
 * @Version 1.0
 */
@Getter
@Setter
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "lhl-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机号
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 序列化器
     */
    private String serializer = RpcConstant.JDK;
}
