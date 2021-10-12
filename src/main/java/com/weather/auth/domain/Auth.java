package com.weather.auth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Auth {
    private long userId;
    private String accessToken;
    private String refreshToken;
    private long expiration;

    public Auth (String accessToken, String refreshToken, long expiration) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
