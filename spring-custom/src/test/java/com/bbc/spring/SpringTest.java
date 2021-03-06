package com.bbc.spring;

import com.bbc.spring.framework.BeanFactory;
import com.bbc.spring.framework.DefaultListableBeanFactory;
import com.bbc.spring.po.Student;
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
        Student student = (Student) beanFactory.getBean("student");
        System.out.println(student.toString());
    }
}
