package com.bbc.mybatis.po;

/**
 * @author fitbbc
 * @date 2019/05/16
 */
public class OrderExt extends Order {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
