package org.example.spring1114.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CheckLoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginStatus())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/code","/doLogin","/doRegister");
    }
}
