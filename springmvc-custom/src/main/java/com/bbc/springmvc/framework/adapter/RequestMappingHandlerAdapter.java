package com.bbc.springmvc.framework.adapter;

import com.bbc.spring.framework.BeanFactory;
import com.bbc.spring.framework.aware.BeanFactoryAware;
import com.bbc.springmvc.framework.annotation.ResponseBody;
import com.bbc.springmvc.framework.handlermapping.HandlerMethod;
import com.bbc.springmvc.framework.util.JsonUtils;
import com.bbc.springmvc.handler.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter, BeanFactoryAware {

    private BeanFactory beanFactory;
    @Override
    public boolean support(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String handlerName = handlerMethod.getHandlerName();
        Object bean = beanFactory.getBean(handlerName);

        try {
            Object[] params = handleParameter(method, request);
            Object resultValue = method.invoke(bean, params);
            handleResultValue(resultValue, method, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void handleResultValue(Object resultValue, Method method, HttpServletResponse response) {
        try {
            if (method.isAnnotationPresent(ResponseBody.class)){
                Class<?> returnType = method.getReturnType();
                if (returnType == Map.class){
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(JsonUtils.object2Json(resultValue));
                }else if (returnType == String.class){
                    response.setContentType("text/plain; charset=utf-8");
                    response.getWriter().write(resultValue.toString());
                }
            }else{
                //页面展示
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object[] handleParameter(Method method, HttpServletRequest request) {
        List<Object> params = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters){
            String name = parameter.getName();
            String[] value = parameterMap.get(name);
            Class<?> type = parameter.getType();
            if (type == List.class){
                params.add(Arrays.asList(value));
            }else if (type == Integer.class){
                params.add(Integer.valueOf(value[0]));
            }else if (type == String.class){
                params.add(value[0]);
            }
        }
        return params.toArray();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
