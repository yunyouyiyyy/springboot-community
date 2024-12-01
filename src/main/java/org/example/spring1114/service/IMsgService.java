package org.example.spring1114.service;

import org.example.spring1114.bean.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMsgService {
    List<Message> getAllMessages();
    // 添加留言
    void addMessage(Message message);
    // 处理上传的图片并返回图片的相对路径
    String handleImageUpload(MultipartFile image) throws IOException;
    // 创建留言并保存
    void createMessage(String username, String content, String imageUrl);
}
