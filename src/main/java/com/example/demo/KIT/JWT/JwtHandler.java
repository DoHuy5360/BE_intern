package com.example.demo.KIT.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.KIT.TRAY.EmployeeAccountTray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;;

public class JwtHandler {

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

    public static EmployeeAccountTray verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);
            String subjectObject = decodedJWT.getSubject();
            // Giải mã chuỗi subject từ Base64

            Object de = (Object) subjectObject;
            System.out.println(de);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                EmployeeAccountTray subjectData = objectMapper.readValue(subjectObject, EmployeeAccountTray.class);
                return subjectData;
            } catch (JsonProcessingException e) {
                // Xử lý ngoại lệ JsonProcessingException ở đây
                e.printStackTrace();
                return new EmployeeAccountTray();
                // Hoặc ném ra ngoại lệ khác hoặc thông báo lỗi cho người dùng tương ứng
            }
            // EmployeeAccountTray subjectData = ((EmployeeAccountTray) de);

        } catch (TokenExpiredException e) {
            // Xử lý token hết hạn
            // ...
            throw new RuntimeException("Token đã hết hạn: " + e.getMessage(), e);
        } catch (SignatureVerificationException e) {
            // Xử lý chữ ký không đúng
            // ...
            throw new RuntimeException("Chữ ký không đúng: " + e.getMessage(), e);
        } catch (JWTVerificationException e) {
            // Xử lý các lỗi xác thực JWT khác
            // ...
            throw new RuntimeException("Xác thực token không thành công: " + e.getMessage(), e);
        }
    }

}
