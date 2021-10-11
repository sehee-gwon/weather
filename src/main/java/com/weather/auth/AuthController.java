package com.weather.auth;

import com.weather.auth.domain.Auth;
import com.weather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(authService.login(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody Auth auth) {
        try {
            return ResponseEntity.ok(authService.reissue(auth));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
