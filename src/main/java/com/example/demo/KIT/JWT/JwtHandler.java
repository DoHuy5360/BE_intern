package com.example.demo.kit.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.kit.dotenv.DotenvHandler;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;;

public class JwtHandler {

    // private static final String SECRET_KEY = DotenvHandler.get("JWT_SECRET_KEY");
    private static final String SECRET_KEY = "abcxyz123";

    public static String generateToken(String subject, long expirationTimeMillis) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);

        JWTCreator.Builder builder = JWT.create()
                .withSubject(subject)
                .withExpiresAt(expirationDate);
        String re = builder.sign(algorithm);
        return re;
    }

    public static JwtResponse verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);
            String subjectObject = decodedJWT.getSubject();
            // Giải mã chuỗi subject từ Base64
            System.out.println("jwtHandler.java:" + subjectObject);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                EmployeeAccountTray subjectData = objectMapper.readValue(subjectObject, EmployeeAccountTray.class);

                // todo: subjectData.setAccountEmail();
                return new JwtResponse(true, subjectData, new Response(HttpStatus.OK, Message.READ_SUCCESS));
            } catch (JsonProcessingException e) {
                // Xử lý ngoại lệ JsonProcessingException ở đây
                e.printStackTrace();

                return new JwtResponse(false, new Response(HttpStatus.BAD_REQUEST, Message.READ_FAIL));
                // Hoặc ném ra ngoại lệ khác hoặc thông báo lỗi cho người dùng tương ứng
            }
            // EmployeeAccountTray subjectData = ((EmployeeAccountTray) de);

        } catch (TokenExpiredException e) {
            // Xử lý token hết hạn
            // ...
            return new JwtResponse(false, new Response(HttpStatus.BAD_REQUEST, Message.setExpiredMessage("Token")));
        } catch (SignatureVerificationException e) {
            // Xử lý chữ ký không đúng
            // ...
            return new JwtResponse(false, new Response(HttpStatus.BAD_REQUEST, Message.setWrongMessage("Signature")));
        } catch (JWTVerificationException e) {
            // Xử lý các lỗi xác thực JWT khác
            // ...
            return new JwtResponse(false,
                    new Response(HttpStatus.BAD_REQUEST, Message.setFailMessage("Verify Token is")));
        }
    }

}