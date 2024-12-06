package org.example.spring1114.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ImageMessage {
    private int id; // 主键ID
    private int userId; // 用户ID
    private String content; // 留言内容
    private String imagePath; // 图片路径
    private Timestamp createdAt; // 创建时间

    public ImageMessage(int userId, String message, long time, String imagePath) {
        this.userId = userId;
        this.content = message;
        this.imagePath = imagePath;
        this.createdAt = new Timestamp(time); // 将毫秒值转换为 Timestamp

    }

    public ImageMessage() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getTimeStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdAt);
    }
}
