package com.weather.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class JwtUser {
    @NotEmpty
    @Email
    @Size(max = 60)
    private String loginId;

    @NotEmpty
    @Size(max = 120)
    private String password;
}
