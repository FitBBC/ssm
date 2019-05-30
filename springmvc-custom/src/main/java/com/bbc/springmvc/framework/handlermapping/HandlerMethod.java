package com.bbc.springmvc.framework.handlermapping;

import java.lang.reflect.Method;

/**
 * @author fitbbc
 * @date 2019/05/30
 */
public class HandlerMethod {

    private Method method;

    private String handlerName;

    public HandlerMethod(Method method, String handlerName) {
        this.method = method;
        this.handlerName = handlerName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
}
