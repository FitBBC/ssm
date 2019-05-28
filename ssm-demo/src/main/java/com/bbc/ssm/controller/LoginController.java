package com.bbc.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    // 登录
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password) {
        // Service进行用户身份验证
        // 把用户信息保存到session中
        session.setAttribute("username", username);
        // 重定向到商品列表页面
        return "redirect:/item/findItem";
    }
    // 退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //清空session
        session.invalidate();
        // 重定向到登录页面
        return "redirect:/login.jsp";
    }
}