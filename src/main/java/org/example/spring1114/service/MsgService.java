package org.example.spring1114.service;


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
    public void sendMessage(String username, String message, String imagePath) {
        ImageMessage messageObj = new ImageMessage(1,message,System.currentTimeMillis(),imagePath) ;
        iMsgDAO.add(messageObj);
    }
}
