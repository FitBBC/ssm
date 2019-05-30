package com.bbc.springmvc.framework.handlermapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fitbbc
 * @date 2019/05/29
 */
public interface HandlerMapping {
    public Object getHandler(HttpServletRequest request);
}
