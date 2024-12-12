package org.example.spring1114.service;



import jakarta.servlet.http.HttpSession;
import org.example.spring1114.bean.ImageMessageDTO;

import java.util.List;

public interface IMsgService {

    List<ImageMessageDTO> listAll();

    void sendMessage(String username, String message, String imagePath, HttpSession session);

}
