package com.bbc.springmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public interface HttpRequestHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
