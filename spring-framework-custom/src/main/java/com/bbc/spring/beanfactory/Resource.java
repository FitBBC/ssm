package com.bbc.spring.beanfactory;

import java.io.InputStream;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public interface Resource {

    boolean isHandleLocation();

    InputStream getInputStream();
}
