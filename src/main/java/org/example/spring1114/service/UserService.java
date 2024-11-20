package org.example.spring1114.service;

import jakarta.servlet.http.HttpSession;
import org.example.spring1114.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

//    //
//    private static final  UserService userService = new UserService();
//    public static UserService getInstance(){
//        return userService;
//    }

    private UserService(){};

    private UserDAO userDAO = new UserDAO();


    public boolean login(String username, String password) {
        String pwd = userDAO.getPasswordByUsername(username);
        return pwd != null && pwd.equals(password);
    }

    public void register(String username, String password) {
        if (userDAO.userExists(username)) {
            throw new RuntimeException("用户名已存在");
        } else {
            userDAO.addUser(username, password);
        }
        System.out.println("注册成功");
    }
}
