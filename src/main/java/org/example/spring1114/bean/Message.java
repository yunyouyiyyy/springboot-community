package org.example.spring1114.bean;

import jakarta.persistence.*;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne // 多对一 关系
    @JoinColumn(name = "user_id") // 映射到 Message 表中的 user_id 外键
    private User user;  // 用户对象，通过外键关联

    @Column(name = "content")
    private String content;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_at")
    private Timestamp createAt;


    public Message() {
    }

    // Getter 和 Setter 方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

}