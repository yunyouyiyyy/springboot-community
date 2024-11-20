package org.example.spring1114;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /uploads/** 映射到本地路径 E:/code/spring1114/uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:E:/code/spring1114/uploads/");
    }
}
