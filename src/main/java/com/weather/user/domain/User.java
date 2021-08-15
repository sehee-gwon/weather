package com.weather.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long userId;
    private String loginId;
    private String password;
    private String userName;
    private String email;
    private String phoneNumber;
    private String createdDate;
}
