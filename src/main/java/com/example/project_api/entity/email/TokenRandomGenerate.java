package com.example.project_api.entity.email;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenRandomGenerate {
    private static final String CHARACTERS = "0123456789BDEFGHRSTUVWXYZabcdefghituvwxyz";

    private static final int LENGTH = 15;

    @Bean
    public static String generateCustomUUID() {
        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
