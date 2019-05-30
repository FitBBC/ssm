package com.bbc.spring.framework;

import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    public Object getBean(String name) {
        return null;
    }

    public Object getBean(Class<?> clazz) {
        return null;
    }

    public <T> List<T> getBeansByType(Class<?> clazz){ return null; }

    public List<String> getBeanNamesByType(Class<?> clazz){ return null; }
}
