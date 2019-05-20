package com.bbc.spring.beanfactory;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class PropertyValue {

    private String name;
    private Object vlaue;

    public PropertyValue(String name, Object vlaue) {
        this.name = name;
        this.vlaue = vlaue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getVlaue() {
        return vlaue;
    }

    public void setVlaue(Object vlaue) {
        this.vlaue = vlaue;
    }
}
