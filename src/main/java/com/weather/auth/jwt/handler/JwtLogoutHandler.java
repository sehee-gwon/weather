package com.weather.auth.jwt.handler;

import com.weather.auth.AuthService;
import com.weather.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtLogoutHandler extends SimpleUrlLogoutSuccessHandler {
    private final AuthService authService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws ServletException, IOException {
        // 토큰 만료 처리
        authService.deleteAuth(request, response);

        super.onLogoutSuccess(request, response, authentication);
    }
}
