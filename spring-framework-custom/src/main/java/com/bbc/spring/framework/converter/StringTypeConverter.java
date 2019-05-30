package com.bbc.spring.framework.converter;

public class StringTypeConverter implements TypeConverter {
    public boolean isType(Class<?> clazz) {
        if (clazz == String.class) return true;
        return false;
    }

    public Object convert(String source) {
        return source;
    }
}
