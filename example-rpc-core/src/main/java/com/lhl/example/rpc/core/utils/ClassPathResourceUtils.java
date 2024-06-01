package com.lhl.example.rpc.core.utils;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description 通过配置文件路径获取配置文件信息
 * @Author hl133
 * @Date 2024/6/1-15:57
 * @Version 1.0
 */
public class ClassPathResourceUtils {

    private static ClassPathResource classPathResource = null;
    private static InputStream stream = null;

    /**
     * 通过配置文件路径获取配置文件信息
     * @param propertiesPath springboot配置文件的路径
     */
    public static Properties getResourcePropertiesInfo(String propertiesPath) {
        classPathResource = new ClassPathResource(propertiesPath);
        Properties properties = new Properties();
        try {
            // 获取输入流
            stream = classPathResource.getStream();
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // properties.clear()
            try{
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
