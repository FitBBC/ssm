package com.bbc.ssm.resolver;

import com.bbc.ssm.exception.BusinessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BusinessExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        //自定义预期异常
        BusinessException businessException = null;
        //如果抛出的是系统自定义的异常
        if (ex instanceof BusinessException) {
            businessException = (BusinessException) ex;
        } else {
            businessException = new BusinessException("未知错误");
        }
        ModelAndView modelAndView = new ModelAndView();
        //把错误信息传递到页面
        modelAndView.addObject("message", businessException.getMessage());
        //指向错误页面
        modelAndView.setViewName("error");
        return modelAndView;
    }
}