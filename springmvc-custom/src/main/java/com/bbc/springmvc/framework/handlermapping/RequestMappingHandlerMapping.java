package com.bbc.springmvc.framework.handlermapping;

import com.bbc.spring.framework.BeanFactory;
import com.bbc.spring.framework.aware.BeanFactoryAware;
import com.bbc.spring.framework.beandefinition.BeanDefinition;
import com.bbc.springmvc.framework.annotation.Controller;
import com.bbc.springmvc.framework.annotation.RequestMapping;
import com.bbc.springmvc.handler.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware {

    private BeanFactory beanFactory;
    private Map<String, HandlerMethod> handlerMap = new HashMap<>();

    public void init(){
        try {
            //先获取所有的类
            List<String> beanNames = beanFactory.getBeanNamesByType(Object.class);
            for (String beanName : beanNames){
                //过滤有注解controller和requestMapping的类
                BeanDefinition beanDefinition = beanFactory.getBeanDefinitionByBeanName(beanName);
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                if (clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(RequestMapping.class)){
                    //获取该类所有方法
                    Method[] methods = clazz.getDeclaredMethods();
                    //过滤有注解requestMapping的方法
                    for (Method method : methods){
                        if (method.isAnnotationPresent(RequestMapping.class)){
                            //解析requestMapping注解,获取url
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String url = requestMapping.value();
                            //把method和beanName封装到对象HandlerMethod中
                            HandlerMethod handlerMethod = new HandlerMethod(method, beanName);
                            //url=handlerMethod放入map中
                            handlerMap.put(url, handlerMethod);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Object getHandler(HttpServletRequest request){
        return handlerMap.get(request.getRequestURI());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
