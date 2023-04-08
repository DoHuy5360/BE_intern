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
        private String apiPathV1 = "/api/v1";
        private String apiPathV2 = "/api/v2";
        private String employeePath = getPathOf(apiPathV2, "/employee");
        @Autowired
        private Authentication authentication;
        @Autowired
        private AdminAuthorization adminAuthorization;
        @Autowired
        private UserAuthorization userAuthorization;

        public String getPathOf(String api, String name) {
                return api + name;
        }

        @Override
        public void addInterceptors(InterceptorRegistry interceptorRegistry) {
                interceptorRegistry.addInterceptor(authentication)
                                // .addPathPatterns("/**")
                                .addPathPatterns("/login")
                                // .addPathPatterns("/api/v1")
                                .excludePathPatterns("/public/**");

                interceptorRegistry.addInterceptor(adminAuthorization)
                                .addPathPatterns("/api/v1/account/**")
                                // ! employee
                                .addPathPatterns(employeePath + "/")
                                .addPathPatterns(employeePath + "/{id}/show")
                                .addPathPatterns(employeePath + "/store")
                                .addPathPatterns(employeePath + "/multiple-store")
                                .addPathPatterns(employeePath + "/all-information")
                                .addPathPatterns(employeePath + "/{id}/delete")
                                .addPathPatterns(employeePath + "/{id}/update")
                                .excludePathPatterns("/public/**");

                interceptorRegistry.addInterceptor(userAuthorization)
                                .addPathPatterns("/decode")
                                // ! employee
                                .addPathPatterns(employeePath + "/information")
                                .addPathPatterns(employeePath + "/update-self")
                                .excludePathPatterns("/public/**");
        }
}
