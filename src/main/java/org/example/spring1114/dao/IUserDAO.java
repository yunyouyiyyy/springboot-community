package org.example.spring1114.dao;

public interface IUserDAO {
    String getPasswordByUsername(String username);
    boolean userExists(String username);
    void addUser(String username, String password);
    Integer getUserIdByUsername(String username);
}
