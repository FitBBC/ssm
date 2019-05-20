package com.bbc.spring.beanfactory;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(Class<?> clazz);
}
