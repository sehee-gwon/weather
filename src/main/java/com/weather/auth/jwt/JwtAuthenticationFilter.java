package com.weather.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.user.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        // 1. objectMapper 를 사용하여 data 읽어옴
        User user = objectMapper.readValue(request.getInputStream(), User.class);

        // 2. loginId, password 를 기반으로 인증 전 객체 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getLoginId(), user.getPassword());

        // 3. 사용자 비밀번호 체크 (authenticate 메서드가 실행될 때 UserDetailsService 의 loadUserByUsername 메서드가 실행됨)
        // 인증 전 객체를 받아서 인증 완료된 Authentication 객체를 반환
        return this.getAuthenticationManager().authenticate(authToken);
    }
}
