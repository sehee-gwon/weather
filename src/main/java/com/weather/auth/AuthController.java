package com.weather.auth;

import com.weather.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("login")
    public String login(@RequestBody User user) {


        return "login";
    }
}
