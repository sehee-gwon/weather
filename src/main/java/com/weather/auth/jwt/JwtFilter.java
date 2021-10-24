package com.weather.auth.jwt;

import com.weather.auth.AuthService;
import com.weather.auth.domain.Auth;
import com.weather.common.util.CookieUtil;
import com.weather.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final AuthService authService;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 1. Request Header 에서 토큰을 꺼냄
        String accessToken = resolveToken(request, "accessToken");
        String refreshToken = resolveToken(request, "refreshToken");
        String userId = resolveToken(request, "userId");

        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(accessToken)) {
            try {
                if (JwtUtil.validateToken(accessToken)) {
                    Authentication authentication = JwtUtil.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                log.error("JWT expired error : {} ", e);

                try {
                    // 토큰 만료시 재발급 (refresh token 으로 access token 재발급)
                    if (JwtUtil.validateToken(refreshToken)) {
                        Auth auth = new Auth();
                        auth.setAccessToken(accessToken);
                        auth.setRefreshToken(refreshToken);
                        auth = authService.reissueAuth(auth, response);

                        // 쿠키 생성
                        CookieUtil.createCookie("accessToken", auth.getAccessToken(), JwtUtil.ACCESS_TOKEN_COOKIE_EXPIRE_TIME, true, response);
                        CookieUtil.createCookie("refreshToken", auth.getRefreshToken(), JwtUtil.REFRESH_TOKEN_COOKIE_EXPIRE_TIME, true, response);
                        CookieUtil.createCookie("userId", String.valueOf(auth.getUserId()), JwtUtil.REFRESH_TOKEN_COOKIE_EXPIRE_TIME, false, response);

                        Authentication authentication = JwtUtil.getAuthentication(auth.getAccessToken());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception ex) {
                    authService.deleteAuth(request, response);  // 토큰 만료 처리
                    log.error("토큰 만료시 재발급 에러: {}", ex.getMessage(), ex);
                }
            } catch (Exception e) {
                authService.deleteAuth(request, response);  // 토큰 만료 처리
                SecurityContextHolder.clearContext();
            }
        } else {
            CookieUtil.deleteCookie(request, response);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request, String name) {
        String token = null;
        Cookie cookie = CookieUtil.getCookie(request, name);
        if (cookie != null) {
            token = cookie.getValue();
        }
        return token;
    }
}

/*
OncePerRequestFilter 인터페이스를 구현하기 때문에 요청 받을 때 단 한번만 실행됩니다.
doFilterInternal
    실제 필터링 로직을 수행하는 곳입니다.
    Request Header 에서 Access Token 을 꺼내고 여러가지 검사 후 유저 정보를 꺼내서 SecurityContext 에 저장합니다.
    가입/로그인/재발급을 제외한 모든 Request 요청은 이 필터를 거치기 때문에 토큰 정보가 없거나 유효하지 않으면 정상적으로 수행되지 않습니다.
    그리고 요청이 정상적으로 Controller 까지 도착했다면 SecurityContext 에 Member ID 가 존재한다는 것이 보장됩니다.
    대신 직접 DB 를 조회한 것이 아니라 Access Token 에 있는 Member ID 를 꺼낸 거라서, 탈퇴로 인해 Member ID 가 DB 에 없는 경우 등 예외 상황은 Service 단에서 고려해야 합니다.
*/