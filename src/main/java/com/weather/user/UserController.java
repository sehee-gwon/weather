package com.weather.user;

import com.weather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Validated User user, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception("잘못된 접근입니다.");
            }
            if (userService.getUserByLoginId(user.getLoginId()) != null) {
                throw new Exception("이미 사용중인 아이디입니다.");
            }

            userService.insertUser(user);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/check")
    public ResponseEntity check(@RequestParam String loginId) {
        return ResponseEntity.ok(userService.getUserByLoginId(loginId));
    }
}
