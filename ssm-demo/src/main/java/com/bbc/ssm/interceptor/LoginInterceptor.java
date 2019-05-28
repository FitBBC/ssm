package com.bbc.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //1、 如果请求的URL是公开地址(无需登录就可以访问的URL)，采取放行。
        if(requestURI.indexOf("login")>-1) return true;
        //2、 如果用户session存在，则放行。
        String username = (String) request.getSession().getAttribute("username");
        if (username != null && !username.equals("")) return true;
        //3、 如果用户session中不存在，则跳转到登录页面。
        response.sendRedirect("/login.jsp");
        return false;
    }
    //其他代码略
}