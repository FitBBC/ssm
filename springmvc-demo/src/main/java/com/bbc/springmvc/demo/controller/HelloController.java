package com.bbc.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fitbbc
 * @date 2019/05/26
 */
@Controller
public class HelloController {

    @RequestMapping("showView")
    public String showView(){
        return "hello";
    }

    @RequestMapping("showData")
    @ResponseBody
    public String showData(){
        return "showData";
    }
}
