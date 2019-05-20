package com.bbc.spring.beanfactory;

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
}
