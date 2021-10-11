package com.weather.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtTokenUtil {

    /**
     * SecurityContext 에서 username 조회 (user_id)
     * @return
     */
    public static Long getUserId() {
        // SecurityContext 에서 토큰의 인증 정보 조회
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("SecurityContext 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}
