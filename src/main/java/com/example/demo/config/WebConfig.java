package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.config.middleware.auth.Authentication;
import com.example.demo.config.middleware.auth.UserAuthorization;
import com.example.demo.config.middleware.auth.AdminAuthorization;

@Configuration
public class WebConfig implements WebMvcConfigurer {
        @Autowired
        private Authentication authentication;
        @Autowired
        private AdminAuthorization adminAuthorization;
        @Autowired
        private UserAuthorization userAuthorization;

        @Override
        public void addInterceptors(InterceptorRegistry interceptorRegistry) {
                interceptorRegistry.addInterceptor(authentication)
                                // .addPathPatterns("/**")
                                .addPathPatterns("/login")
                                // .addPathPatterns("/api/v1")
                                .excludePathPatterns("/public/**");

                interceptorRegistry.addInterceptor(adminAuthorization)
                                .addPathPatterns("/decode")
                                .addPathPatterns("/api/v1/account/**")
                                .excludePathPatterns("/public/**");

                interceptorRegistry.addInterceptor(userAuthorization)
                                .addPathPatterns("/api/v1/employee")
                                .excludePathPatterns("/public/**");
        }
}
