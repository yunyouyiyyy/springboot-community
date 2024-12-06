package org.example.spring1114.dao;

import org.example.spring1114.bean.ImageMessage;
import org.example.spring1114.bean.ImageMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SpringJdbcImageMessageDaoImpl implements IMsgDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<ImageMessageDTO> listAll() {
//        String sql = " select * from message";
        String sql = " select message.*,user.username from message left join user on message.user_id = user.id";
        System.out.println(jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ImageMessageDTO.class)));
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ImageMessageDTO.class));
    }

    @Override
    public void add(ImageMessage messageObj) {
        String sql = "insert into message(user_id,content,image_path) value(?,?,?)";
        jdbcTemplate.update(sql,messageObj.getUserId(),messageObj.getContent(),messageObj.getImagePath());
    }
}
