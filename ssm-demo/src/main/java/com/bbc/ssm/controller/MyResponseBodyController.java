package com.bbc.ssm.controller;

import com.bbc.ssm.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("responsebody")
@Controller
public class MyResponseBodyController {

    // @RequestMapping注解中的consumes和produces分别是为请求头和响应头设置contentType
    @RequestMapping(value = "returnString", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String returnString() {
        // 如果在使用@ResponseBody注解的前提下，如果返回值是String类型，则返回值会由 StringHttpMessageConverter进行处理
        return "查询失败";
    }

    @RequestMapping("returnPOJO")
    @ResponseBody
    public User returnPOJO() {
        User user = new User();
        user.setId(1);
        user.setUsername("bingbing");
        user.setSex("女");
        // 如果在使用@ResponseBody注解的前提下，如果返回值是POJO类型，则返回值会由 MappingJacksonHttpMessageConverter(需要第三方jar包支持)进行处理
        return user;
    }
}