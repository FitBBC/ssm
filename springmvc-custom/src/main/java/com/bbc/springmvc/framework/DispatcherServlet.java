package com.bbc.springmvc.framework;

import com.bbc.spring.framework.DefaultListableBeanFactory;
import com.bbc.springmvc.framework.adapter.HandlerAdapter;
import com.bbc.springmvc.framework.handlermapping.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public class DispatcherServlet extends HttpServlet {

    private List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    private List<HandlerMapping> handlerMappingList  = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 从ioc容器中获取配置的bean
        String configLocation = config.getInitParameter("configLocation");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(configLocation);
        //处理器适配器集合
        handlerAdapterList = beanFactory.getBeansByType(HandlerAdapter.class);
        //处理器映射器集合
        handlerMappingList = beanFactory.getBeansByType(HandlerMapping.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        // 循环找到对应的适配器
        for (HandlerAdapter handlerAdapter : handlerAdapterList){
            if (handlerAdapter.support(handler)){
                return handlerAdapter;
            }
        }
        return null;
    }

    private Object getHandler(HttpServletRequest request) {
        // uri是可变的,不能写死,那么就需要配置,(xml,properties, 注解)多种配置方式
        // 需要多个帮手查找处理器(HandlerMapping --- 根据请求找到对应的处理器)
        // 每种方式都对应一个HandlerMapping,每个HandlerMapping都有一个uri->处理器的map集合
        // 策略模式
        for (HandlerMapping handlerMapping : handlerMappingList){
            Object handler = handlerMapping.getHandler(request);
            if (handler != null){
                return handler;
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收客户端发送过来的请求,获取uri
        //根据uri查找对应的请求处理类(普通的javabean,不需要继承或者实现servlet)
        Object handler = getHandler(request);
        //调用请求处理类方法处理请求--适配器模式
        //先根据处理器类型,找到对应的适配器(一个类型的处理器,对应一个适配器)
        HandlerAdapter ha = getHandlerAdapter(handler);
        //处理响应结果
        ha.handleRequest(handler, request, response);

    }


}
