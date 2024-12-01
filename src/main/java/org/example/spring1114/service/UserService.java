package org.example.spring1114.service;

import org.example.spring1114.bean.User;
import org.example.spring1114.dao.UserRepository;
import org.example.spring1114.event.RegEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    // 注入 ApplicationEventPublisher
    private final ApplicationEventPublisher eventPublisher;
    public UserService(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    };

    public boolean login(String username, String password) {
        // 使用 userRepository 查找用户
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public void register(String username, String password, String email) {
        // 使用 userRepository 检查用户是否存在
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("用户名已存在");
        } else {
            // 创建并保存新用户
            User user = new User(username, password, email);
            userRepository.save(user);

            // 模拟用户注册逻辑
            System.out.println("User registered: " + email);

            // 发布注册事件
            eventPublisher.publishEvent(new RegEvent(this, email));
        }
        System.out.println("注册成功");
    }
}
