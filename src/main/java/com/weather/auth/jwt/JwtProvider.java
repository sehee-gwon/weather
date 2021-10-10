package com.weather.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.security.Key;

@Component
public class JwtProvider {

    public void createToken() {
        Key key = new Key() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }
        };

       // Keys.hmacShaKeyFor()

        String accessToken = Jwts.builder()
                .setSubject("auth")
                .claim(null ,null)
                .setExpiration(null)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
