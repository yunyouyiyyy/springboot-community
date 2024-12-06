package org.example.spring1114.dao;

import org.example.spring1114.bean.ImageMessage;
import org.example.spring1114.bean.ImageMessageDTO;

import java.util.List;

public interface IMsgDAO {
    List<ImageMessageDTO> listAll();

    void add(ImageMessage messageObj);
}
