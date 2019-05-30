package com.bbc.spring.framework;

import com.bbc.spring.framework.beandefinition.BeanDefinition;

import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(Class<?> clazz);

    <T> List<T> getBeansByType(Class<?> clazz);

    List<String> getBeanNamesByType(Class<?> clazz);

    BeanDefinition getBeanDefinitionByBeanName(String name);
}
