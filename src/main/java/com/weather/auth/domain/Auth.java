package com.weather.auth.domain;

import lombok.Builder;

@Builder
public class Auth {
    private final String accessToken;
    private final String refreshToken;
    private final long expiration;
}
