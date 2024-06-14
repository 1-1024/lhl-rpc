package com.lhl.example.rpc.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * @Description 配置工具类
 * @Author hl133
 * @Date 2024/5/31-17:35
 * @Version 1.0
 */
public class ConfigUtils {

    /**
     * @Author hl133
     * @Description // 加载配置对象
     * @Date 17:38 2024/5/31
     * @Param tClass
     * @Param prefix
     * @return: T
     **/
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * @Author hl133
     * @Description // 加载配置对象，支持区分环境
     * @Date 17:46 2024/5/31
     * @Param tClass:
     * @Param prefix:
     * @Param env:
     * @return: T
     **/
    public static <T> T loadConfig(Class<T> tClass, String prefix, String env) {
        StringBuilder config = new StringBuilder("application");
        if(StrUtil.isNotBlank(env)) {
            config.append("-").append(env);
        }
        config.append(".properties");

        Props props = new Props(config.toString());
        return props.toBean(tClass, prefix);
    }

}
