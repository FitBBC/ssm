package com.bbc.spring.framework.resource;

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
        // 获取xml文件流
        String resource = location.substring(10);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        return inputStream;
    }
}
