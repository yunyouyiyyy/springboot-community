
package org.example.spring1114.service;

import org.example.spring1114.dao.IUserDAO;
import org.example.spring1114.event.RegEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserDAO userDao;
    public UserService(@Qualifier("springJdbcUserDao") IUserDAO userDao) {
        this.userDao = userDao;
    }
    public void register(String username, String password,String email) {
        if (!userDao.userExists(username)) {
            userDao.addUser(username, password);
        } else {
            throw new IllegalArgumentException("⽤户已存在");
        }
    }
    public boolean login(String username, String password) {
        String pwd = userDao.getPasswordByUsername(username);
        return pwd != null && pwd.equals(password);
    }
}