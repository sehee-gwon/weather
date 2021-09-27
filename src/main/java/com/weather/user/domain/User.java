package com.weather.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long userId;
    private String userName;
    private String loginId;
    private String password;
    private String createdDate;
}
