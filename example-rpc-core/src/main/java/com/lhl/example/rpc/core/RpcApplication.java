package com.lhl.example.rpc.core;

import com.lhl.example.rpc.core.config.RpcConfig;
import com.lhl.example.rpc.core.constant.RpcConstant;
import com.lhl.example.rpc.core.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description RPC框架应用，双检索单例模式实现，存放项目全局用到变量
 * @Author hl133
 * @Date 2024/5/31-17:55
 * @Version 1.0
 */
public class RpcApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(RpcApplication.class);

    private static volatile RpcConfig rpcConfig;


    /**
     * @Author hl133
     * @Description // 框架初始化，支持传入自定义配置
     * @Date 21:39 2024/5/31
     * @Param newRpcConfig:
     * @return: void
     **/
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        LOGGER.info("rpc init, config = {}", newRpcConfig.toString());
    }

    /**
     * @Author hl133
     * @Description //初始化
     * @Date 18:58 2024/5/31
     * @return: void
     **/
    public static void init() {
        RpcConfig newRpcConfig;
        try{
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception exception) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * @Author hl133
     * @Description // 获取配置
     * @Date 21:38 2024/5/31
     * @return: com.lhl.example.rpc.core.config.RpcConfig
     **/
    public static RpcConfig getRpcConfig() {
        if(rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if(rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
