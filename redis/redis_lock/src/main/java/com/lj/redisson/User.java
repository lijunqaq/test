package com.lj.redisson;

import lombok.Data;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/11/5
 */
@Data
public class User {
    private String name;
    private Integer id;
    private String type;

    public User(String name, Integer id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
