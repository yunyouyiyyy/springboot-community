package org.example.spring1114.dao;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("jdbcUserDao")
public class JdbcUserDaoImpl implements IUserDAO {
    // 数据库连接的 URL、⽤户名和密码
    private final String url = "jdbc:mysql://localhost:3306/cqrkdb"; // 数据库的 JDBCURL
    private final String user = "root"; // 数据库⽤户名
    private final String password = "200434zyl"; // 数据库密码
    @Override
    public String getPasswordByUsername(String username) {
        // SQL 查询语句，⽤于根据⽤户名获取密码
        String query = "SELECT password FROM user WHERE username = ?";
        try (
                // 获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, password);
                // 准备 SQL 语句
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            // 设置 SQL 查询中的参数
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) { // 执⾏查询并获取结果集
                if (rs.next()) { // 如果有结果，返回查询到的密码
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            // 打印 SQL 异常的堆栈信息
            e.printStackTrace();
        }
        // 如果查询不到结果，返回 null
        return null;
    }
    @Override
    public boolean userExists(String username) {
        // SQL 查询语句，⽤于检查⽤户是否存在
        String query = "SELECT 1 FROM user WHERE username = ?";
        try (
                // 获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, password);
                // 准备 SQL 语句
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            // 设置 SQL 查询中的参数
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) { // 执⾏查询并获取结果集
                return rs.next(); // 如果有结果，说明⽤户存在，返回 true
            }
        } catch (SQLException e) {
            // 打印 SQL 异常的堆栈信息
            e.printStackTrace();
        }
        // 如果出现异常或者⽤户不存在，返回 false
        return false;
    }
    @Override
    public void addUser(String username, String password) {
        // SQL 语句，⽤于添加新⽤户
        String query = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (
                // 获取数据库连接
                Connection conn = DriverManager.getConnection(url, user, this.password);
                // 准备 SQL 语句
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            // 设置 SQL 语句中的参数
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate(); // 执⾏更新操作
        } catch (SQLException e) {
            // 打印 SQL 异常的堆栈信息
            e.printStackTrace();
        }
    }
}