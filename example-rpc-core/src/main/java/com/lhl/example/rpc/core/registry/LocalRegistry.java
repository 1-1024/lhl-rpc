package com.lhl.example.rpc.core.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 本地注册中心：使用线程安全的ConcurrentHashMap存储服务注册信息，key为服务名称、value为服务的实现类。之后就可以根据要调用的服务名称获取到对应的实现类，然后通过反射进行方法调用
 * @Author hl133
 * @Date 2024/5/11-18:18
 * @Version 1.0
 */
public class LocalRegistry {
    /*
        本地服务注册器和注册中心的作用是有区别的。注册中心的作用侧重于管理注册的服务、提供服务信息给消费者
        而本地服务注册器的作用是根据服务名获取到对应的实现类，完成调用必不可少的模块
     */

    /**
     * 存储注册信息
     */
    private final static Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * @Author hl133
     * @Description // 注册服务
     * @Date 20:10 2024/5/11
     * @Param serviceName: 服务名字
     * @Param aClass: 实现类
     * @return: void
     **/
    public static void register(String serviceName, Class<?> aClass) {
        map.put(serviceName, aClass);
    }

    /**
     * @Author hl133
     * @Description // 获取服务
     * @Date 20:11 2024/5/11
     * @Param serviceName: 服务名称
     * @return: java.lang.Class<?> 实现类
     **/
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * @Author hl133
     * @Description // 删除服务
     * @Date 20:12 2024/5/11
     * @Param serviceName: 服务名称
     * @return: void
     **/
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
