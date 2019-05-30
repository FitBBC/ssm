package com.bbc.springmvc.framework.handlermapping;

import com.bbc.spring.framework.BeanFactory;
import com.bbc.spring.framework.aware.BeanFactoryAware;
import com.bbc.springmvc.handler.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public class SimpleHandlerMapping implements HandlerMapping, BeanFactoryAware {

    private BeanFactory beanFactory;
    private Map<String, Object> handlerMap = new HashMap<>();

    public void init(){
        //加载映射信息到Map集合中
        //handlerMap.put("/UserAdd", new UserAddHandler());
        //handlerMap.put("/UserDelete", new UserDeleteHandler());
        List<String> beanNames = beanFactory.getBeanNamesByType(HttpRequestHandler.class);
        for (String beanName: beanNames){
            if (beanName.startsWith("/")){
                Object bean = beanFactory.getBean(beanName);
                handlerMap.put(beanName, bean);
            }
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
