package com.weather.auth;

import com.weather.auth.domain.Auth;
import com.weather.auth.jwt.JwtProvider;
import com.weather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthMapper authMapper;
    private final JwtProvider jwtProvider;

    @Transactional
    public Auth login (User user) {
        // 1. Login ID/PW 를 기반으로 authToken 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getLoginId(), user.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        Auth auth = jwtProvider.createToken(authentication);

        // 4. RefreshToken 저장
        Auth refreshAuth = new Auth();
        refreshAuth.setUserId(Long.parseLong(authentication.getName()));
        refreshAuth.setRefreshToken(auth.getRefreshToken());
        authMapper.insertAuth(refreshAuth);

        // 5. 토큰 발급
        return auth;
    }

    @Transactional
    public Auth reissue (Auth requestAuth) {
        // 1. Refresh Token 검증
        if (!jwtProvider.validateToken(requestAuth.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 User ID 가져오기
        Authentication authentication = jwtProvider.getAuthentication(requestAuth.getAccessToken());

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
        Auth auth = jwtProvider.createToken(authentication);

        // 6. 저장소 정보 업데이트
        auth.setUserId(refreshAuth.getUserId());
        authMapper.updateAuth(auth);

        // 7. 토큰 발급
        return auth;
    }
}
