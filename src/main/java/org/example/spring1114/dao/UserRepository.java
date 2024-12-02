package org.example.spring1114.dao;

import org.example.spring1114.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 根据用户名查找用户
    User findByUsername(String username);

    // 检查用户是否存在
    boolean existsByUsername(String username);
}
