package com.bbc.springmvc.controller;

import com.bbc.springmvc.framework.annotation.Controller;
import com.bbc.springmvc.framework.annotation.RequestMapping;
import com.bbc.springmvc.framework.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fitbbc
 * @date 2019/05/30
 */
@Controller
public class UserController {

    @RequestMapping("/queryUser")
    @ResponseBody
    public Map<String, Object> queryUser(Integer id, String username){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        return map;
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(Integer id){
        return "OK";
    }
}
