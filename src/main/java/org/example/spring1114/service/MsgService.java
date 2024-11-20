package org.example.spring1114.service;

import org.example.spring1114.bean.Message;
import org.example.spring1114.dao.MsgDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class MsgService implements IMsgService {

    private final MsgDAO msgDAO;

    @Autowired
    public MsgService(MsgDAO msgDAO) {
        this.msgDAO = msgDAO;
    }

    // 获取所有留言
    public List<Message> getAllMessages() {
        return msgDAO.getAllMessages();
    }

    // 添加留言
    public void addMessage(Message message) {
        msgDAO.addMessage(message);
    }

    // 处理上传的图片并返回图片的相对路径
    public String handleImageUpload(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // 保存图片文件
            String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File savedFile = new File(uploadDir, imageName);
            image.transferTo(savedFile);

            // 返回图片的相对路径
            return "/uploads/" + imageName;
        }
        return null;
    }

    // 创建留言并保存
    public void createMessage(String username, String content, String imageUrl) {
        Message message = new Message(username, content, System.currentTimeMillis(), imageUrl);
        addMessage(message);
    }
}
