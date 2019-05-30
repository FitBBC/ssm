package com.bbc.spring.framework.converter;

public interface TypeConverter {

    boolean isType(Class<?> clazz);

    Object convert(String source);
}
