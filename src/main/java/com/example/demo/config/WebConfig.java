package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.config.middleware.auth.Authentication;
import com.example.demo.config.middleware.auth.EmployeeAuthorization;
import com.example.demo.config.middleware.auth.ManagerAuthorization;
import com.example.demo.config.middleware.password.ResetPasswordAuthentication;

@Configuration
public class WebConfig implements WebMvcConfigurer {
        private String apiPathV1 = "/api/v1";
        private String apiPathV2 = "/api/v2";
        private String employeePath = getPathOf(apiPathV2, "/employee");
        private String headquarterPath = getPathOf(apiPathV2, "/headquarter");
        private String workSchedulePath = getPathOf(apiPathV2, "/workschedule");
        private String accountPath = getPathOf(apiPathV2, "/account");
        @Autowired
        private Authentication authentication;
        @Autowired
        private ManagerAuthorization adminAuthorization;
        @Autowired
        private EmployeeAuthorization userAuthorization;
        @Autowired
        private ResetPasswordAuthentication resetPasswordAuthentication;

        public String getPathOf(String api, String name) {
                return api + name;
        }

        @Bean
        public CorsFilter corsFilter() {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowCredentials(true);
                // corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
                corsConfiguration.addAllowedOrigin("http://localhost:3000");
                corsConfiguration.addAllowedOrigin("https://urbanscheduler-k6mvt1ny9-huynhthang1910.vercel.app");
                corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
                corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
                corsConfiguration.addAllowedOriginPattern("http://localhost:3000");
                corsConfiguration.addAllowedOriginPattern("https://urbanscheduler-k6mvt1ny9-huynhthang1910.vercel.app");
                corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                corsConfiguration.setAllowedOrigins(
                                Arrays.asList("https://urbanscheduler-k6mvt1ny9-huynhthang1910.vercel.app"));
                UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
                urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
                return new CorsFilter(urlBasedCorsConfigurationSource);
        }

        public void addInterceptors(InterceptorRegistry interceptorRegistry) {
                // .addPathPatterns("/**")
                interceptorRegistry.addInterceptor(authentication)
                                .addPathPatterns("/login")
                                .excludePathPatterns("/public/**");

                // todo: Only Manager role can access to these route.
                interceptorRegistry.addInterceptor(adminAuthorization)
                                .addPathPatterns(employeePath + "/")
                                .addPathPatterns(employeePath + "/{id}/show")
                                .addPathPatterns(employeePath + "/store")
                                .addPathPatterns(employeePath + "/multiple-store")
                                .addPathPatterns(employeePath + "/all-information")
                                .addPathPatterns(employeePath + "/{id}/delete")
                                .addPathPatterns(employeePath + "/{id}/update")
                                .addPathPatterns(headquarterPath + "/")
                                .addPathPatterns(headquarterPath + "/store")
                                .addPathPatterns(headquarterPath + "/{id}/update")
                                .addPathPatterns(headquarterPath + "/{id}/detele")
                                .addPathPatterns(workSchedulePath + "/")
                                .addPathPatterns(workSchedulePath + "/all-information")
                                .addPathPatterns(workSchedulePath + "/{id}/show")
                                .addPathPatterns(accountPath + "/")
                                .addPathPatterns(accountPath + "/{id}/show")
                                .addPathPatterns(accountPath + "/store")
                                .addPathPatterns(accountPath + "/{id}/update")
                                .addPathPatterns(accountPath + "/{id}/delete")
                                .excludePathPatterns("/public/**");
                // todo: Employee & Manager can access to these route.
                interceptorRegistry.addInterceptor(userAuthorization)
                                .addPathPatterns("/decode")
                                .addPathPatterns(employeePath + "/information")
                                .addPathPatterns(employeePath + "/self-update")
                                .addPathPatterns(employeePath + "/create-avatar")
                                .addPathPatterns(employeePath + "/avatar/{employeeId}")
                                .addPathPatterns(workSchedulePath + "/store")
                                .addPathPatterns(workSchedulePath + "/self-schedule")
                                .addPathPatterns(workSchedulePath + "/{id}/delete")
                                .addPathPatterns(workSchedulePath + "/{id}/update")
                                .addPathPatterns(accountPath + "/reset-password")
                                .excludePathPatterns("/public/**");
                interceptorRegistry.addInterceptor(resetPasswordAuthentication)
                                .addPathPatterns(accountPath + "/change-password");
        }
}
