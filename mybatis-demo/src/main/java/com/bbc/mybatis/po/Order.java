package com.bbc.mybatis.po;

import java.io.Serializable;

public class Order implements Serializable {
    private Integer oid;

    private String name;

    private Integer userId;

    public Order(Integer oid, String name, Integer userId) {
        this.oid = oid;
        this.name = name;
        this.userId = userId;
    }

    public Order() {
        super();
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}