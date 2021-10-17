package com.weather.auth;

import com.weather.auth.domain.Auth;
import com.weather.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;
    private final JwtUtil jwtUtil;

    @Transactional
    public Auth reissue (Auth requestAuth) {
        // 1. Refresh Token 검증
        if (!jwtUtil.validateToken(requestAuth.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 User ID 가져오기
        Authentication authentication = jwtUtil.getAuthentication(requestAuth.getAccessToken());

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
        Auth auth = jwtUtil.createToken(authentication);

        // 6. 저장소 정보 업데이트
        auth.setUserId(refreshAuth.getUserId());
        authMapper.updateAuth(auth);

        // 7. 토큰 발급
        return auth;
    }

    public void deleteAuthByUserId(long userId) {
        authMapper.deleteAuthByUserId(userId);
    }
}
