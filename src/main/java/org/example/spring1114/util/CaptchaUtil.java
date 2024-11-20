package org.example.spring1114.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaUtil {

    // 生成验证码图片
    public static BufferedImage createCaptchaImage(String captchaCode, int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        // 背景颜色
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        // 绘制验证码字符
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString(captchaCode, 40, 28);

        // 添加干扰线
        drawNoise(g, width, height);

        g.dispose();
        return bufferedImage;
    }

    // 生成随机验证码字符串
    public static String generateCaptchaCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        return captcha.toString();
    }

    // 绘制干扰线
    private static void drawNoise(Graphics g, int width, int height) {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}