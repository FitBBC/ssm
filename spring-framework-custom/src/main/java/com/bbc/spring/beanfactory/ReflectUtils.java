package com.bbc.spring.beanfactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class ReflectUtils {
    public static Object createObject(String beanClassName) {
        try {
            Class<?> clazz = Class.forName(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setProperty(Object beanInstance, String name, Object valueToUse) {
        try {
            Field field = beanInstance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(beanInstance, valueToUse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
