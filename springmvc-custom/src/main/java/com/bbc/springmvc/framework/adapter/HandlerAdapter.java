package com.bbc.springmvc.framework.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public interface HandlerAdapter {

    boolean support(Object handler);

    void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)throws IOException;
}
