package com.weather.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.security.Key;

@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public void createToken() {
       // Keys.hmacShaKeyFor()

        String accessToken = Jwts.builder()
                .setSubject("auth")
                .claim(null ,null)
                .setExpiration(null)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
