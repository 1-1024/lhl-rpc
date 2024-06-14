package com.lhl.example.rpc.core.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description 服务代理工厂
 * @Author hl133
 * @Date 2024/5/12-19:30
 * @Version 1.0
 */
public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
