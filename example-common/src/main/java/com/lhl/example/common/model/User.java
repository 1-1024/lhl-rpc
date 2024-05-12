package com.lhl.example.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description 用户
 * @Author hl133
 * @Date 2024/5/11-14:43
 * @Version 1.0
 */
@Getter
@Setter
public class User implements Serializable {

    private String name;

    public User(String name) {
        this.name = name;
    }
}
