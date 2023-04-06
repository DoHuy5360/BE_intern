package com.example.demo.KIT.JWT;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtGenerator {

    private static final String SECRET_KEY = "secretkey"; // Thay thế bằng khóa bí mật của bạn

    public static String generateToken(String subject, long expirationTimeMillis) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);

        JWTCreator.Builder builder = JWT.create()
                .withSubject(subject)
                .withExpiresAt(expirationDate);
        String re = builder.sign(algorithm);
        return re;
    }

    // Phương thức để xác thực và giải mã JWT token
    // (chưa hoàn chỉnh, cần kiểm tra và bắt các exception thích hợp)
    public static String verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String subject = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
        return subject;
    }

}
