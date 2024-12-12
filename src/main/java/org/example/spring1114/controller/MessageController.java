package org.example.spring1114.controller;

import jakarta.servlet.http.HttpSession;
import org.example.spring1114.bean.ImageMessageDTO;
import org.example.spring1114.service.IMsgService;
import org.example.spring1114.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MessageController {

    // 读取 application.properties 中的配置
    @Value("${demo.file.upload-path}")
    private String uploadDir;

    @Autowired
    private IMsgService imageMessageService;

    @RequestMapping("/message")
    public String list(Model model) {

        List<ImageMessageDTO> messages = imageMessageService.listAll();
        model.addAttribute("messages", messages);
        return "message";
    }


    @PostMapping("/sendImageMessage") // 定义一个处理 POST 请求的方法，用于发送图像消息
    public String sendMessage(String message, MultipartFile image, HttpSession session) {
        // 从 session 中获取当前用户的用户名，如果没有登录则设置为“未登录用户”
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "未登录用户";
        }
        String imagePath = null; // 初始化图像路径变量
        // 检查上传的图像文件是否有效（即不为空且存在文件内容）
        if (image != null && !image.isEmpty()) {
            // 获取上传目录在服务器中的真实路径
            File realUploadDirFile = new File(uploadDir);
            // 如果上传目录不存在，则创建该目录
            if (!realUploadDirFile.exists()) {
                realUploadDirFile.mkdirs();
            }
            System.out.println("uploadDir = " + uploadDir);
            System.out.println("realUploadDirFile.getAbsolutePath() = " + realUploadDirFile.getAbsolutePath());

            // 生成带时间戳的唯一文件名，避免重名冲突
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            try {
                // 创建目标文件对象，并将上传的文件内容写入该文件
                File dest = new File(realUploadDirFile, fileName);
                System.out.println("dest.getAbsolutePath() = " + dest.getAbsolutePath());


                image.transferTo(dest); // 将上传的文件内容保存到目标文件
                imagePath = fileName;  // 相对路径
            } catch (IOException e) {
                throw new RuntimeException("保存文件失败:" + e.getMessage()); // 异常处理，保存文件失败时抛出运行时异常
            }

        }
        System.out.println("imagePath = " + imagePath);
        // 调用服务层方法，发送带有用户名、消息内容及图像路径的消息
        imageMessageService.sendMessage(username, message, imagePath,session);

        // 重定向到 "imageMessage" 页面，返回视图名称
        return "redirect:message";
    }


    @PostMapping("/sendImageMessage00")
    public String sendMessage00(String message, MultipartFile image, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "未登录用户";
        }

        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            String uploadDir = "uploads/";
            String realDirPath = session.getServletContext().getRealPath(uploadDir);

            File realUploadDirFile = new File(realDirPath);
            if (!realUploadDirFile.exists()) {
                realUploadDirFile.mkdirs();

            }
            System.out.println("uploadDirFile.getAbsolutePath() = " + realUploadDirFile.getAbsolutePath());
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            try {
                File dest = new File(realUploadDirFile, fileName);
                System.out.println("dest.getAbsolutePath() = " + dest.getAbsolutePath());
                dest.createNewFile();
                image.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException("保存文件失败:" + e.getMessage());
            }
            imagePath = uploadDir + File.separatorChar + fileName;
        }

        imageMessageService.sendMessage(username, message, imagePath,session);
        return "redirect:imageMessage";
    }
}
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msgContent = req.getParameter("message");
        long time = System.currentTimeMillis();
        Object user = req.getSession().getAttribute("username");
        if(user == null){
            user = "未登录用户";
        }
        Message message = new Message(user.toString(),msgContent,time);
        messages.add(message);
        req.setAttribute("messages",messages);
        req.getRequestDispatcher("message.jsp").forward(req,resp);
    }*/