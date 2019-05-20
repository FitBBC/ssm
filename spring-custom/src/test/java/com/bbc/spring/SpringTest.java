package com.bbc.spring;

import com.bbc.spring.beanfactory.BeanFactory;
import com.bbc.spring.beanfactory.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class SpringTest {

    @Test
    public void test(){
        String location = "classpath:Spring.xml";
        BeanFactory beanFactory = new DefaultListableBeanFactory(location);
    }
}
