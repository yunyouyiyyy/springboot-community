package org.example.spring1114.dao;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Repository
public class UserDAO implements IUserDAO {

    //这里我不太明白为什么要用ConcurrentHashMap，而不是HashMap
    private final Map<String, String> userPwdMap = new ConcurrentHashMap<>();//存储用户名和密码的键值对

    @Override
    public String getPasswordByUsername(String username) {
        return userPwdMap.get(username);
    }

    @Override
    public boolean userExists(String username) {
        return userPwdMap.containsKey(username);
    }

    @Override
    public void addUser(String username, String password) {
        userPwdMap.put(username, password);
    }
}