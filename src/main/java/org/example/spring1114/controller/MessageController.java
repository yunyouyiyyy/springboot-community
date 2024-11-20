package org.example.spring1114.controller;

import jakarta.servlet.http.HttpSession;
import org.example.spring1114.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class MessageController {

    private final MsgService msgService;

    @Autowired
    public MessageController(MsgService msgService) {
        this.msgService = msgService;
    }

    @Autowired
    private HttpSession session;

    @GetMapping("/message")
    public String showMessages(Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("msg", "请先登录");
            return "login";
        }
        model.addAttribute("messages", msgService.getAllMessages());
        return "message";
    }

    @PostMapping("/message")
    public String addMessage(
            @RequestParam("message") String content,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Model model) throws IOException {

        String username = (String) session.getAttribute("username");

        // 调用 Service 层处理图片上传
        String imageUrl = msgService.handleImageUpload(image);

        // 调用 Service 层创建留言并保存
        msgService.createMessage(username, content, imageUrl);

        model.addAttribute("messages", msgService.getAllMessages());
        return "message";
    }
}
