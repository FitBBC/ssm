package com.bbc.ssm.controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//第二种Handler处理器的编写方式:实现HttpRequestHandler接口
public class DemoController implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //数据填充
        request.setAttribute("msg","实现HttpRequestHandler接口的开发方式" );
        request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
    }
}