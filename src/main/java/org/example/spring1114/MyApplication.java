package org.example.spring1114;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;

@ServletComponentScan//扫描servlet
@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Value("${spring.web.RegisterUrl}")
    private String RegisterUrl;
    @Value("${spring.auto.openurl}")
    private boolean isOpen;


    @Override
    public void run(String... args) throws IOException {

        if (isOpen) {
            System.out.println("自动加载指定的页面");
            // 判断操作系统是否为 Windows
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows 系统下使用 cmd 命令打开浏览器
                Runtime.getRuntime().exec("cmd /c start " + RegisterUrl);
            } else {
                System.out.println("浏览器页面异常");
            }
        }
    }
}

