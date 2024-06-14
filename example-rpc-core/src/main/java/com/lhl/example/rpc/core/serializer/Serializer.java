package com.lhl.example.rpc.core.serializer;

import java.io.IOException;

/**
 * @Description 序列化器接口
 * @Author hl133
 * @Date 2024/5/11-20:58
 * @Version 1.0
 */
public interface Serializer {

    /**
     * @Author hl133
     * @Description // 序列化
     * @Date 20:59 2024/5/11
     * @Param object: 对象
     * @return: byte[] 序列化后的对象
     **/
    <T> byte[] serialize(T object) throws IOException;

    /**
     * @Author hl133
     * @Description // 反序列化
     * @Date 21:01 2024/5/11
     * @Param bytes:
     * @Param type:
     * @return: T
     **/
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
