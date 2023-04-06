package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.config.middleware.auth.Authentication;
import com.example.demo.config.middleware.auth.Authorization;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private Authentication authentication;
    @Autowired
    private Authorization authorization;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(authentication)
                // .addPathPatterns("/**") // Định nghĩa các đường dẫn cần áp dụng interceptor
                .addPathPatterns("/login") // Định nghĩa các đường dẫn cần áp dụng interceptor
                .excludePathPatterns("/public/**"); // Định nghĩa các đường dẫn không cần áp dụng interceptor
        interceptorRegistry.addInterceptor(authorization)
                .addPathPatterns("/decode")
                .excludePathPatterns("/public/**");
    }
}
