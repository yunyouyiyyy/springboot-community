package org.example.spring1114.bean;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private String username;
    private long time;
    private String message;
    private String formattedTime;
    private String imageUrl;

    // 添加一个 SimpleDateFormat 对象，用于格式化时间
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Message(String username, String message, long time,String imageUrl) {
        this.username = username;
        this.time = time;
        this.message = message;
        this.formattedTime = sdf.format(new Date(time));
        this.imageUrl = imageUrl;  // 初始化新字段
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }
    //imageUrl的getter和setter方法
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}