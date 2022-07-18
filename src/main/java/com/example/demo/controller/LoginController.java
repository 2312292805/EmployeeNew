package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    //实现页面跳转到登陆成功页明
    @RequestMapping("/user/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        Model model, HttpSession session){
        //具体实现功能
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //告诉用户，登陆失败
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }
    }
    //实现注销的工鞥
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
