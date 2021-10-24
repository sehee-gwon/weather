package com.weather.common.util;

import com.weather.auth.domain.Auth;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private static final String AUTHORITIES_KEY = "auth";
    public static final int ACCESS_TOKEN_COOKIE_EXPIRE_TIME = 60 * 60 * 24;
    public static final int REFRESH_TOKEN_COOKIE_EXPIRE_TIME = 60 * 60 * 24 * 7;
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 10 ;            // 30min
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7days

    public static Key key = null;

    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public static Auth createToken(Authentication authentication, HttpServletResponse response) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // Access Token
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // 쿠키 생성
        CookieUtil.createCookie("accessToken", accessToken, ACCESS_TOKEN_COOKIE_EXPIRE_TIME, true, response);
        CookieUtil.createCookie("refreshToken", refreshToken, REFRESH_TOKEN_COOKIE_EXPIRE_TIME, true, response);
        CookieUtil.createCookie("userId", String.valueOf(authentication.getName()), REFRESH_TOKEN_COOKIE_EXPIRE_TIME, false, response);

        return new Auth(accessToken, refreshToken, accessTokenExpiresIn.getTime());
    }

    public static Authentication getAuthentication(String accessToken) {
        // Token Decryption
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // Claim에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw e;
        }
    }

    public static Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static Long getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}

/*
JWT 토큰에 관련된 암호화, 복호화, 검증 로직은 다 이곳에서 이루어집니다.
생성자
    application.yml 에 정의해놓은 jwt.secret 값을 가져와서 JWT 를 만들 때 사용하는 암호화 키값을 생성합니다.
generateTokenDto
    유저 정보를 넘겨받아서 Access Token 과 Refresh Token 을 생성합니다.
    넘겨받은 유저 정보의 authentication.getName() 메소드가 username 을 가져옵니다.
    저는 username 으로 Member ID 를 저장했기 때문에 해당 값이 설정될 겁니다.
    Access Token 에는 유저와 권한 정보를 담고 Refresh Token 에는 아무 정보도 담지 않습니다.
getAuthentication
    JWT 토큰을 복호화하여 토큰에 들어 있는 정보를 꺼냅니다.
    Access Token 에만 유저 정보를 담기 때문에 명시적으로 accessToken 을 파라미터로 받게 했습니다.
    Refresh Token 에는 아무런 정보 없이 만료일자만 담았습니다.
    UserDetails 객체를 생생성해서 UsernamePasswordAuthenticationToken 형태로 리턴하는데 SecurityContext 를 사용하기 위한 절차라고 생각하면 됩니다..
    사실 좀 불필요한 절차라고 생각되지만 SecurityContext 가 Authentication 객체를 저장하기 때문에 어쩔수 없습니다.
    parseClaims 메소드는 만료된 토큰이어도 정보를 꺼내기 위해서 따로 분리했습니다.
validateToken
    토큰 정보를 검증합니다.
    Jwts 모듈이 알아서 Exception 을 던져줍니다.
*/
