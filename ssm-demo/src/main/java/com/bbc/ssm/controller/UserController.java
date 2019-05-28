package com.bbc.ssm.controller;

import com.bbc.ssm.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用来学习参数绑定 * @author think *
 */
@RequestMapping("user")
@Controller
public class UserController {
    @RequestMapping("findUserById")
    public String findUserById(Integer id, Model model, HttpServletRequest request) {
        model.addAttribute("msg", "直接参数绑定接收到的参数:" + id);
        model.addAttribute("msg", "通过Request getParameter参数接收到的参 数:" + request.getParameter("id"));
        return "success";
    }

    // @RequestParam:可以理解为request.getParameter("参数key") @RequestMapping("findUserById2")
    public String findUserById2(@RequestParam("uid") Integer id, Model model) {
        model.addAttribute("msg", "接收到的参数:" + id);
        return "success";
    }

    @RequestMapping("saveUser")
    public String saveUser(User user, Model model) {
        model.addAttribute("msg", "接收到的参数:" + user.toString());
        return "success";
    }

    @RequestMapping("deleteUser")
    public String deleteUser(String birthday, Model model) {
        model.addAttribute("msg", "接收到的参数:" + birthday);
        return "success";
    }

    @RequestMapping("deleteUser2")
    public String deleteUser2(Date birthday, Model model) {
        model.addAttribute("msg", "接收到的参数:" + birthday);
        return "success";
    }

    @RequestMapping("findUserByIds")
    public String findUserByIds(Integer[] id, Model model) {
        model.addAttribute("msg", "接收到的参数:" + id);
        return "success";
    }

    @RequestMapping("findUserByIds2")
    public String findUserByIds2(List<Integer> id, Model model) {
        model.addAttribute("msg", "接收到的参数:" + id);
        return "success";
    }

    @RequestMapping("findUserByIds3")
    public String findUserByIds3(User user, Model model) {
        model.addAttribute("msg", "接收到的参数:" + user.getUid());
        return "success";
    }

    @RequestMapping("updateUser")
    public String updateUser(User user, Model model) {
        model.addAttribute("msg", "接收到的参数:" + user.getUid());
        return "success";
    }
}