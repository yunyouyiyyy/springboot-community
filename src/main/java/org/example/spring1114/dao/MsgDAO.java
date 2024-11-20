package org.example.spring1114.dao;

import org.example.spring1114.bean.Message;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository  // 注解表示这是一个 DAO 层的类
public class MsgDAO implements IMsgDAO {

    //具体ArrayList的使用方法我不清楚可以参考：https://www.runoob.com/java/java-collections.html
    private List<Message> messages = new ArrayList<>();

    // 构造函数，初始化默认留言
    public MsgDAO() {
        messages.add(new Message("zhang3", "你好", System.currentTimeMillis(),null));
        messages.add(new Message("li4", "你也好", System.currentTimeMillis(),null));
        messages.add(new Message("管理员", "只留言，不聊天哟", System.currentTimeMillis(),null));
    }

    // 获取所有留言
    public List<Message> getAllMessages() {
        return messages;
    }

    // 添加留言
    public void addMessage(Message message) {
        messages.add(message);
    }
    // 添加图片
    public void addImage(Message message) {
        messages.add(message);
    }
}