package org.example.spring1114.service;

public interface IUserService {

    boolean login(String username, String password);
    void register(String username, String password,String email);
}
