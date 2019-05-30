package com.bbc.spring.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class ReflectUtils {
    /**
     * 根据类全路径名获取实例
     * @param beanClassName
     * @return
     */
    public static Object createObject(String beanClassName) {
        try {
            Class<?> clazz = Class.forName(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反射给实例赋值
     * @param beanInstance
     * @param name
     * @param valueToUse
     */
    public static void setProperty(Object beanInstance, String name, Object valueToUse) {
        try {
            Field field = beanInstance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(beanInstance, valueToUse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取字段的类型
     * @param beanClassName
     * @param name
     * @return
     */
    public static Class<?> getTypeByFiledName(String beanClassName, String name) {
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射执行实例方法
     * @param beanInstance
     * @param initMethod
     */
    public static void invokeMethod(Object beanInstance, String initMethod) {
        try {
            Class<?> clazz = beanInstance.getClass();
            Method method = clazz.getDeclaredMethod(initMethod);
            method.setAccessible(true);
            method.invoke(beanInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
