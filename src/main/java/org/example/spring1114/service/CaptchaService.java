package org.example.spring1114.service;


import org.example.spring1114.util.CaptchaUtil;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class CaptchaService {

    public BufferedImage generateCaptcha(String captchaCode, int width, int height) {
        return CaptchaUtil.createCaptchaImage(captchaCode, width, height);
    }

    public String generateCaptchaCode(int length) {
        return CaptchaUtil.generateCaptchaCode(length);
    }
}
