package com.bbc.spring.beanfactory;

public interface TypeConverter {

    boolean isType(Class<?> clazz);

    Object convert(String source);
}
