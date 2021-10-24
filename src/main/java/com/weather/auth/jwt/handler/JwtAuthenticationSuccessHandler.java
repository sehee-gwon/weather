package com.weather.auth.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.auth.AuthService;
import com.weather.auth.domain.Auth;
import com.weather.common.util.CookieUtil;
import com.weather.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 1. 로그인 성공시, 토큰 정보 생성
        Auth auth = JwtUtil.createToken(authentication, response);

        // 2. RefreshToken 정보 저장
        auth.setUserId(Long.parseLong(authentication.getName()));
        authService.saveAuth(auth);

        // 3. 토큰 정보 response data 세팅
        objectMapper.writeValue(response.getWriter(), auth);
    }
}
