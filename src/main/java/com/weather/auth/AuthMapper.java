package com.weather.auth;

import com.weather.auth.domain.Auth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    /**
     * 토큰 인증 정보 저장
     * @param auth
     */
    void saveAuth(Auth auth);

    /**
     * 토큰 인증 정보 조회
     * @param userId
     * @return
     */
    Auth getAuthByUserId(long userId);

    /**
     * Refresh Token 제거 
     * @param userId
     */
    void deleteAuthByUserId(long userId);
}
