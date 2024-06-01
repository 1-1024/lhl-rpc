package com.lhl.example.rpc.core.server;

import com.lhl.example.rpc.model.RpcRequest;
import com.lhl.example.rpc.model.RpcResponse;
import com.lhl.example.rpc.registry.LocalRegistry;
import com.lhl.example.rpc.serializer.JdkSerializer;
import com.lhl.example.rpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Description Http 请求处理器
 * @Author hl133
 * @Date 2024/5/11-22:16
 * @Version 1.0
 */
public class HttpServiceHandle implements Handler<HttpServerRequest> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServiceHandle.class);

    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();
        // 记录日志
        LOGGER.info("Received request : {}, {}", request.method(), request.uri());
        // 异步处理 Http 请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 构造响应结果
            RpcResponse rpcResponse = new RpcResponse();

            // 如果请求为null，直接返回
            if(rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            try {
                // 获取要调用的服务现实类，通过反射调用
                Class<?> aClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = aClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object data = method.invoke(aClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(data);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("OK");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setExceptionMessage(e);
            }
            // 响应
            doResponse(request, rpcResponse, serializer);
        });

    }


    /**
     * @Author hl133
     * @Description // 响应处理
     * @Date 11:29 2024/5/12
     * @Param request:
     * @Param rpcResponse:
     * @Param serializer:
     * @return: void
     **/
    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response().putHeader("context-type", "application/json");
        try {
            // 序列化处理
            byte[] serialize = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialize));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }

    }
}
