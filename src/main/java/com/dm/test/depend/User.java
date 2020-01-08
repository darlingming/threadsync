package com.dm.test.depend;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * 一个包装类
 * @author DM wrote on 2019-12-26
 * @version 1.0
 */
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
