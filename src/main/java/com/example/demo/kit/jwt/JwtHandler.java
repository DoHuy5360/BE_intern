package com.example.demo.kit.jwt;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.kit.dotenv.DotenvHandler;
import com.example.demo.kit.query.EmployeeAccountQuery;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;
import com.example.demo.kit.tray.EmployeeAccountTray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;;

@Service
public class JwtHandler {
    @Autowired
    private EmployeeAccountQuery employeeAccountQuery;
    @Autowired
    private JwtResponse jwtResponse;

    public ObjectMapper objectMapper;
    public String subjectObject;
    private JwtResponse jwtResponseOut;
    public JWTCreator.Builder builder;

    // private static final String SECRET_KEY = "abcxyz123";
    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

    public String generateToken(String subject, long expirationTimeMillis) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);

        this.builder = JWT.create()
                .withSubject(subject)
                .withExpiresAt(expirationDate);
        return builder.sign(algorithm);
    }

    public JwtHandler verifyToken(String token) {
        this.jwtResponseOut = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);
            this.subjectObject = decodedJWT.getSubject();

            System.out.println("jwtHandler.java: " + subjectObject);

            this.objectMapper = new ObjectMapper();

            return this;
        } catch (TokenExpiredException e) {
            this.jwtResponseOut = jwtResponse.createJwtResponse(false,
                    new Response(HttpStatus.BAD_REQUEST, Message.setExpiredMessage("Token")));
            return this;
        } catch (SignatureVerificationException e) {
            this.jwtResponseOut = jwtResponse.createJwtResponse(false,
                    new Response(HttpStatus.BAD_REQUEST, Message.setWrongMessage("Signature")));
            return this;
        } catch (JWTVerificationException e) {
            this.jwtResponseOut = jwtResponse.createJwtResponse(false,
                    new Response(HttpStatus.BAD_REQUEST, Message.setFailMessage("Verify Token is")));
            return this;
        }
    }

    public JwtResponse authorizeLogin() {
        if (jwtResponseOut == null) {
            try {
                EmployeeAccountTray subjectData = objectMapper.readValue(this.subjectObject, EmployeeAccountTray.class);
                EmployeeAccountTray oneAC = employeeAccountQuery.getAccountByEmployeeId(subjectData.getEmployeeId())
                        .get(0);
                // todo: fix outbound index 0
                subjectData.setAccountEmail(oneAC.getAccountEmail());
                return jwtResponse.createJwtResponse(true, subjectData,
                        new Response(HttpStatus.OK, Message.READ_SUCCESS));
            } catch (JsonProcessingException e) {
                e.printStackTrace();

                return jwtResponse.createJwtResponse(false, new Response(HttpStatus.BAD_REQUEST, Message.READ_FAIL));
            }
        } else {
            return this.jwtResponseOut;
        }
    }

    public JwtResponse resetPassword() {
        if (jwtResponseOut == null) {
            try {
                JwtEmailCertificateFormat subjectData = objectMapper.readValue(subjectObject,
                        JwtEmailCertificateFormat.class);
                return jwtResponse.createJwtResponse(true, subjectData,
                        new Response(HttpStatus.OK, Message.READ_SUCCESS));
            } catch (JsonProcessingException e) {
                e.printStackTrace();

                return jwtResponse.createJwtResponse(false, new Response(HttpStatus.BAD_REQUEST, Message.READ_FAIL));
            }
        } else {
            return this.jwtResponseOut;
        }
    }

}