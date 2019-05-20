package com.bbc.spring.po;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class Student {

    private String name;
    private Course course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
