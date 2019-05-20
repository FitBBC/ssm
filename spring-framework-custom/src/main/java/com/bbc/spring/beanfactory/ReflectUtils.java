package com.bbc.spring.beanfactory;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class ReflectUtils {
    public static Object createObject(String beanClassName) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
