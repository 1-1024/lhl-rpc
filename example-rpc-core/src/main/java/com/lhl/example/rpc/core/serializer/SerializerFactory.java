package com.lhl.example.rpc.core.serializer;

import com.lhl.example.rpc.core.spi.SpiLoader;

/**
 * @Description JDK序列化器
 * @Author hl133
 * @Date 2024/5/11-21:03
 * @Version 1.0
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}
