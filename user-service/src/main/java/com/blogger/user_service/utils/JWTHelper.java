package com.blogger.user_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTHelper {

//    @Value("${jwt.secret_key}")
    private  String secret;

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .claim("role",role)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*5))
                .signWith(getSecretKey())
                .compact();
    }

    private Key getSecretKey() {
        try {
            KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
            secret= Encoders.BASE64.encode(keyGenerator.generateKey().getEncoded()) ;
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
