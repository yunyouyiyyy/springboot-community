package org.example.spring1114.service;

import org.example.spring1114.event.RegEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
//    /**
//     * 发送简单文本邮件
//     * @param sender  发件人地址
//     * @param to      收件人
//     * @param subject 邮件主题
//     * @param content 邮件内容
//     */
//    public void sendSimpleMail(String sender,String to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(sender); // 发件人邮箱地址
//        message.setTo(to); // 收件人
//        message.setSubject(subject); // 邮件主题
//        message.setText(content); // 邮件内容
//        mailSender.send(message);
//        System.out.println("邮件已发送");
//    }

//    @Value("${spring.mail.username}")
    private String sender="222@qq.com";

    @EventListener
    public void onUserReg(RegEvent event){
        System.out.println("模拟发送邮件:  " + event.getEmail());
//        sendSimpleMail(sender,event.getEmail(),"欢迎注册","记得常来喔!");
    }
}
