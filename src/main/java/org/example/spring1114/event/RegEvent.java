package org.example.spring1114.event;

import org.springframework.context.ApplicationEvent;

public class RegEvent extends ApplicationEvent {
    private final String email;// 注册邮箱

    public RegEvent(Object source, String email) {
        super(source);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
