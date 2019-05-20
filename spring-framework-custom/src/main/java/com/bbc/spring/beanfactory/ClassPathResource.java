package com.bbc.spring.beanfactory;

import java.io.InputStream;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class ClassPathResource implements Resource {

    private String location;

    public ClassPathResource(String location) {
        this.location = location;
    }

    public boolean isHandleLocation() {
        if (location.startsWith("classpath:")) return true;
        return false;
    }

    public InputStream getInputStream() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        return inputStream;
    }
}
