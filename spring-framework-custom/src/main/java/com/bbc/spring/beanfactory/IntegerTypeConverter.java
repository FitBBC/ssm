package com.bbc.spring.beanfactory;

public class IntegerTypeConverter implements TypeConverter {
    public boolean isType(Class<?> clazz) {
        if (clazz == Integer.class) return true;
        return false;
    }

    public Object convert(String source) {
        return Integer.parseInt(source);
    }
}
