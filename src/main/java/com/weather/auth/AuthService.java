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
    public Auth login(User user) {
        // 1. Login ID, PW 를 기반으로 인증 전 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLoginId(), user.getPassword());

        // 2. 사용자 비밀번호 체크 (authenticate 메서드가 실행될 때 UserDetailsService 의 loadUserByUsername 메서드가 실행됨)
        // 인증 전 Authentication 객체를 받아서 인증 완료된 Authentication 객체를 반환
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 jwt 토큰 생성 (accessToken, refreshToken, expiration)
        Auth auth = jwtProvider.createToken(authentication);
        auth.setUserId(Long.parseLong(authentication.getName()));

        // 4. RefreshToken 정보 저장
        authMapper.saveAuth(auth);

        // 5. 토큰 발급
        return auth;
    }

    @Transactional
    public Auth reissue(Auth requestAuth) {
        // 1. Refresh Token 검증
        if (!jwtProvider.validateToken(requestAuth.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 정보가 유효하지 않습니다.");
        }

        // 2. Access Token 에서 UserId 조회
        Authentication authentication = jwtProvider.getAuthentication(requestAuth.getAccessToken());

        // 3. UserId 로 Refresh Token 조회
        Auth auth = authMapper.getAuthByUserId(Long.parseLong(authentication.getName()));
        if (auth == null) {
            throw new RuntimeException("로그아웃된 사용자입니다.");
        }

        // 4. Refresh Token 일치하는지 검사
        if (!auth.getRefreshToken().equals(requestAuth.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성 후 업데이트
        Auth newAuth = jwtProvider.createToken(authentication);
        newAuth.setUserId(auth.getUserId());
        authMapper.saveAuth(newAuth);

        // 6. 토큰 발급
        return newAuth;
    }
}