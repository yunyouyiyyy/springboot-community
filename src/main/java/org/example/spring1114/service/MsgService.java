package org.example.spring1114.service;

import org.example.spring1114.bean.Message;
import org.example.spring1114.bean.User;
import org.example.spring1114.dao.MessageRepository;
import org.example.spring1114.dao.UserRepository;  // 引入 UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class MsgService implements IMsgService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // 使用构造器注入
    @Autowired
    public MsgService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    // 获取所有留言
    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // 添加留言
    @Override
    public void addMessage(Message message) {
        // 使用 messageRepository 保存留言
        messageRepository.save(message);
    }

    // 处理上传的图片并返回图片的相对路径
    @Override
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
    @Override
    public void createMessage(String username, String content, String imageUrl) {
        // 根据用户名查找用户
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 创建 Message 实体对象
        Message message = new Message();
        message.setUser(user); // 设置关联的 User 对象
        message.setContent(content);
        message.setImagePath(imageUrl);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message.setCreateAt(timestamp); // 设置为 Timestamp 类型

        // 保存留言
        addMessage(message);
    }
}
