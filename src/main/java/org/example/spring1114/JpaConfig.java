package org.example.spring1114;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    // 定义 DataSource Bean
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/cqrkdb")  // 修改为你的数据库 URL
                .username("root")  // 修改为你的数据库用户名
                .password("200434zyl")  // 修改为你的数据库密码
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    // 配置 EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);  // 使用 DataSource 配置
        em.setPackagesToScan("org.example.spring1114.bean");  // 扫描实体类所在的包
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    // 配置事务管理器
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
