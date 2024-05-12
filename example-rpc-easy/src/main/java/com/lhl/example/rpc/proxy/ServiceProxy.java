package com.lhl.example.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.lhl.example.rpc.model.RpcRequest;
import com.lhl.example.rpc.model.RpcResponse;
import com.lhl.example.rpc.serializer.JdkSerializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description 服务代理(JDK 动态代理)
 * @Author hl133
 * @Date 2024/5/12-19:24
 * @Version 1.0
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();
        // 构造请求
        RpcRequest request = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try{
            // 序列化
             byte[] bytes = serializer.serialize(request);
            // 请求发送
            try(HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(bytes).execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse response = serializer.deserialize(result, RpcResponse.class);
                return response.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
