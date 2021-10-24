package com.weather.auth;

import com.weather.auth.domain.Auth;
import com.weather.common.util.CookieUtil;
import com.weather.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;

    /**
     * 토큰 재발급
     * @param requestAuth
     * @param response
     */
    @Transactional
    public Auth reissueAuth(Auth requestAuth, HttpServletResponse response) {
        // 1. Refresh Token 검증
        if (!JwtUtil.validateToken(requestAuth.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 User ID 가져오기
        Authentication authentication = JwtUtil.getAuthentication(requestAuth.getAccessToken());

        // 3. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져오기
        Auth refreshAuth = authMapper.getAuthByUserId(Long.parseLong(authentication.getName()));
        if (refreshAuth == null) {
            throw new RuntimeException("로그아웃 된 사용자입니다.");
        }

        // 4. Refresh Token 일치하는지 검사
        if (!refreshAuth.getRefreshToken().equals(requestAuth.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        Auth auth = JwtUtil.createToken(authentication, response);

        // 6. 저장소 정보 업데이트
        auth.setUserId(refreshAuth.getUserId());
        authMapper.saveAuth(auth);

        // 7. 토큰 발급
        return auth;
    }

    /**
     * refresh token DB 저장
     * @param auth
     */
    public void saveAuth(Auth auth) {
        authMapper.saveAuth(auth);
    }

    /**
     * refresh token DB, 토큰 쿠키 삭제
     * @param request
     * @param response
     */
    public void deleteAuth(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteTokenCookie(request, response);

        try {
            if (CookieUtil.getCookie(request,"userId") != null) {
                authMapper.deleteAuthByUserId(Long.parseLong(CookieUtil.getCookieValue(request,"userId")));
            }
        } catch (Exception e) {
            log.error("[/logout] deleteAuthByUserId 토큰 데이터 삭제 Error: {}", e.getMessage(), e);
        }
    }
}
