package org.example.spring1114.controller;


import jakarta.servlet.http.HttpSession;
import org.example.spring1114.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    // 注⼊UserService
    @Autowired
    private UserService userService;
    // 显示注册⻚⾯
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }
    // 处理注册请求
    @RequestMapping("/doRegister")
    public String register(String username, String password, Model model,HttpSession session,@RequestParam("captcha") String captchaInput) {
        String captcha = (String) session.getAttribute("captcha");
        if(captcha == null || !captcha.equals(captchaInput)) {
            model.addAttribute("msg", "验证码错误，请重试！");
            return "register"; // 返回注册页面
        }
        try {
            userService.register(username, password);
            System.out.println("success");
            return "redirect:/login";
        } catch (Exception e) {
            System.out.println("error");
            model.addAttribute("msg", e.getMessage());
            return "register";
        }
    }
    // 显示登录⻚⾯
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/doLogin")
    public String login(String username, String password, Model model, HttpSession session) {
        if (userService.login(username, password)) {
            System.out.println("success");
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            System.out.println("error");
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }
    // 显示首页
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            model.addAttribute("msg", "请先登录");
            return "login"; // 如果 session 中没有用户名，重定向到登录页面
        }

        model.addAttribute("username", username); // 将用户名传递到视图
        return "home";
    }
}