package com.bbc.springmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public class UserDeleteHandler implements HttpRequestHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().write("用户删除成功");
    }
}
