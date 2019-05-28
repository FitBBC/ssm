package com.bbc.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllowOriginInterceptor implements HandlerInterceptor {

    /**
     * 跨域不提交Cookie
     */
    /*public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 有跨域行为时参考网址 http://namezhou.iteye.com/blog/2384434
        if (request.getHeader("Origin") != null) {
            response.setContentType("text/html;charset=UTF-8");
            // 允许哪一个URL
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许那种请求方法
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE");
            response.setHeader("XDomainRequestAllowed", "1");
            System.out.println("正在跨域");
        }
        return true;
    }*/

    /**
     * 跨域提交Cookie
     * Access-Control-Allow-Credentials为true的时候,Access-Control-Allow-Origin一定不能设置为*,否则报错
     * @param request
     * @param response
     * @param arg2
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 有跨域行为时参考网址 http://namezhou.iteye.com/blog/2384434
        if (request.getHeader("Origin") != null) {
            response.setContentType("text/html;charset=UTF-8");
            // 允许哪一个URL 访问request.getHeader("Origin") 根据请求来的url动态允许
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            // 允许那种请求方法
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,HEAD");
            response.setHeader("Access-Control-Max-Age", "0");
            // 允许请求头里的参数列表
            response.setHeader("Access-Control-Allow-Headers",
        "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
            // 允许对方带cookie访问
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("XDomainRequestAllowed", "1");
            System.out.println("正在跨域");
        }
        return true;
    }
}
