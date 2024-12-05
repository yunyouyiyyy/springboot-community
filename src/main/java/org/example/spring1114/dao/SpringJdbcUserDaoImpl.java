package org.example.spring1114.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
// 去掉 JdbcUserDaoImpl的@Primary
@Primary
@Repository("springJdbcUserDao")
public class SpringJdbcUserDaoImpl implements IUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String getPasswordByUsername(String username) {
        // 查询⽤户密码
        String query = "SELECT password FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{username},
                String.class);
    }
    @Override
    public boolean userExists(String username) {
        // 查询⽤户是否存在
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{username},
                Integer.class);
        return count != null && count > 0;
    }
    @Override
    public void addUser(String username, String password) {
        // 插⼊新⽤户
        String query = "INSERT INTO user (username, password) VALUES (?, ?)";
        jdbcTemplate.update(query, username, password);
    }
}
