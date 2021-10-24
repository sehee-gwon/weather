package com.weather.auth.jwt;

import com.weather.auth.AuthService;
import com.weather.auth.domain.Auth;
import com.weather.common.util.CookieUtil;
import com.weather.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final AuthService authService;

    /**
     * 필터링 로직, 토큰 인증 정보를 SecurityContext 에 저장
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 1. Cookie 에서 액세스 토큰을 꺼냄
        String accessToken = CookieUtil.getCookieValue(request, "accessToken");
        String refreshToken = CookieUtil.getCookieValue(request, "refreshToken");
        String userId = CookieUtil.getCookieValue(request, "userId");

        // 2. 액세스 토큰이 있는지 확인
        if (StringUtils.hasText(accessToken)) {
            try {
                // 2-1. 액세스 토큰 유효성 검사 (정상: true, 만료: false, 기타: exception error)
                if (JwtUtil.validateToken(accessToken)) { // 정상
                    Authentication authentication = JwtUtil.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else { // 만료
                    // 2-2. 액세스, 리프레쉬 토큰 재발급 (refresh token 으로 재발급)
                    Auth auth = new Auth();
                    auth.setAccessToken(accessToken);
                    auth.setRefreshToken(refreshToken);

                    auth = authService.reissueAuth(auth, response);

                    Authentication authentication = JwtUtil.getAuthentication(auth.getAccessToken());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) { // 기타 jwt token exception
                // 토큰 만료 처리
                authService.deleteAuth(request, response);
                SecurityContextHolder.clearContext();
                log.error("JwtFilter doFilterInternal Error: {}", e.getMessage(), e);
            }
        } else {
            CookieUtil.deleteCookie(request, response);
        }

        filterChain.doFilter(request, response);
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