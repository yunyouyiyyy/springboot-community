package org.example.spring1114.service;


import jakarta.servlet.http.HttpSession;
import org.example.spring1114.bean.ImageMessage;
import org.example.spring1114.bean.ImageMessageDTO;
import org.example.spring1114.dao.IMsgDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgService implements IMsgService{

    @Autowired
    private IMsgDAO iMsgDAO;

    @Override
    public List<ImageMessageDTO> listAll() {
        return iMsgDAO.listAll();
    }

    @Override
    public void sendMessage(String username, String message, String imagePath,HttpSession session) {
        // 从 session 中获取当前用户的 userId
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("用户未登录");
        }
        ImageMessage messageObj = new ImageMessage(userId,message,System.currentTimeMillis(),imagePath) ;
        iMsgDAO.add(messageObj);
    }
}
