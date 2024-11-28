package org.example.spring1114.service;

import org.example.spring1114.dao.UserDAO;
import org.example.spring1114.event.RegEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    // 注入 ApplicationEventPublisher
    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    };

    private UserDAO userDAO = new UserDAO();


    public boolean login(String username, String password) {
        String pwd = userDAO.getPasswordByUsername(username);
        return pwd != null && pwd.equals(password);
    }

    public void register(String username, String password,String email) {
        if (userDAO.userExists(username)) {
            throw new RuntimeException("用户名已存在");
        } else {
            userDAO.addUser(username, password);
            // 模拟用户注册逻辑
            System.out.println("User registered: " + email);

            // 发布事件
            eventPublisher.publishEvent(new RegEvent(this, email));
        }
        System.out.println("注册成功");
    }


}
