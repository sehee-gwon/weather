package com.weather.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    /**
     * request 발생시 토큰의 인증 정보를 SecurityContext 에 저장
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 1. request header 에서 토큰 정보 조회
        String jwt = resolveToken(request);

        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 인증정보를 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * request header 에서 토큰 정보 조회
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
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