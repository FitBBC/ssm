package com.bbc.spring.po;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class Course {

    private String name;

    private Integer age;

    public void init(){
        System.out.println("我是初始化对象方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
