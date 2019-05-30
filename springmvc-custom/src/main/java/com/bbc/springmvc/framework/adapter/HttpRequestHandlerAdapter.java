package com.bbc.springmvc.framework.adapter;

import com.bbc.springmvc.handler.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public class HttpRequestHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return handler instanceof HttpRequestHandler;
    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ((HttpRequestHandler)handler).handle(request, response);
    }
}
