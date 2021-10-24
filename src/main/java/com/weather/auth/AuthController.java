package com.weather.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    //private final Logger log = LoggerFactory.getLogger(getClass());
    private final AuthService authService;
}
