package org.example.spring1114.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.spring1114.listener.OnlineCntListener;
import org.example.spring1114.service.CaptchaService;
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
    // 注入CaptchaService
    @Autowired
    private CaptchaService captchaService;

    // 处理注册请求
    @RequestMapping("/doRegister")
    public String register(String username,
                           String password,
                           Model model,
                           HttpSession session,
                           String email) {
        try {
            userService.register(username, password, email);
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
    public String loginPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
            model.addAttribute("msg", msg); // 传递到前端
            session.removeAttribute("msg"); // 消息读取后清除
        }
        return "login"; // 返回 Thymeleaf 的 login.html
    }
    @RequestMapping("/doLogin")
    public String login(String username,
                        String password,
                        Model model,
                        HttpSession session,
                        @RequestParam(value = "captcha", required = false) String captchaInput) {

        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        if (loginAttempts == null) {
            loginAttempts = 0; // 初始化为 0
        }
        // 获取保存的验证码
        String captcha = (String) session.getAttribute("captcha");
        // 如果已经尝试过 3 次错误登录，则要求输入验证码
        if (loginAttempts >= 3) {
            if (captcha == null || !captcha.equals(captchaInput)) {
                model.addAttribute("msg", "验证码错误，请重试！");
                model.addAttribute("showCaptcha", true); // 确保验证码显示
                return "login"; // 验证码错误，继续显示验证码
            }
        }
        // 登录验证
        if (userService.login(username, password)) {
            session.removeAttribute("loginAttempts");
            session.removeAttribute("captcha"); // 清除验证码
            session.setAttribute("username", username);
            // 获取当前登录用户的 userId 并存储到 session
            Integer userId = userService.getUserIdByUsername(username); // 通过用户名获取 userId
            session.setAttribute("userId", userId); // 存储 userId

            return "redirect:/home";
        } else {
            session.setAttribute("loginAttempts", loginAttempts + 1);
            model.addAttribute("msg", "用户名或密码错误");

            // 如果错误次数达到 3 次，显示验证码
            if (loginAttempts + 1 >= 3) {
                model.addAttribute("showCaptcha", true); // 控制显示验证码
                String newCaptcha = captchaService.generateCaptchaCode(4); // 生成新验证码
                session.setAttribute("captcha", newCaptcha); // 保存验证码到 session
            } else {
                model.addAttribute("showCaptcha", false); // 不显示验证码
            }

            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        // 清除会话中的用户信息
        session.invalidate();

        // 返回首页并显示登出消息
        model.addAttribute("msg", "您已成功登出！");
        return "login"; // 假设这是首页模板
    }

    // 显示首页
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username); // 将用户名传递到视图
        model.addAttribute("onlineCnt", OnlineCntListener.getCnt());
        return "home";
    }
}