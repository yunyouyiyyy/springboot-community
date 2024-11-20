package org.example.spring1114.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.example.spring1114.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CaptchaController {

    private final CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/code")
    public void generateCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        int width = 160;
        int height = 40;

        // 生成验证码字符串
        String captchaCode = captchaService.generateCaptchaCode(4);

        // 保存到 Session 中
        session.setAttribute("captcha", captchaCode);

        // 生成验证码图片
        BufferedImage captchaImage = captchaService.generateCaptcha(captchaCode, width, height);

        // 设置响应头，禁止缓存
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 设置响应类型为图像
        response.setContentType("image/jpeg");

        // 输出图片到响应流
        ImageIO.write(captchaImage, "jpeg", response.getOutputStream());
    }

}
