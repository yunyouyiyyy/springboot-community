package org.example.spring1114.service;

import org.example.spring1114.bean.Message;

import java.util.List;

public interface IMsgService {
    List<Message> getAllMessages();
    void addMessage(Message message);
}
