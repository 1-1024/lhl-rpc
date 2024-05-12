package com.lhl.example.rpc.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description JDK序列化器
 * @Author hl133
 * @Date 2024/5/11-21:03
 * @Version 1.0
 */
public class JdkSerializer implements Serializer {
    /**
     * @param object
     * @Author hl133
     * @Description // 序列化
     * @Date 20:59 2024/5/11
     * @Param object: 对象
     * @return: byte[] 序列化后的对象
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * @param bytes
     * @param type
     * @Author hl133
     * @Description // 反序列化
     * @Date 21:01 2024/5/11
     * @Param bytes:
     * @Param type:
     * @return: T
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try{
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        } finally {
            objectInputStream.close();
        }
    }
}
