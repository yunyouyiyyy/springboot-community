package org.example.spring1114.dao;

import org.example.spring1114.bean.Message;

import java.util.List;

public interface IMsgDAO {
    List<Message> getAllMessages();
    void addMessage(Message message);
}
