package com.bbc.spring.framework.aware;

import com.bbc.spring.framework.BeanFactory;

/**
 * @author fitbbc
 * @date 2019/05/30
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);
}
