package com.weather.user;

import com.weather.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info(Model model) {
        List<User> list = null;

        try {
            list = userService.getUserList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        model.addAttribute("list", list);
        return "OK";
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Validated User user, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception("잘못된 접근입니다.");
            }
            if (userService.checkDuplicate(user.getLoginId()) > 0) {
                throw new Exception("이미 사용중인 아이디입니다.");
            }

            userService.insertUser(user);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/check")
    public ResponseEntity checkDuplicate(@RequestParam String loginId) {
        return ResponseEntity.ok(userService.checkDuplicate(loginId));
    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestParam String userLogin, @RequestParam String userPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        return ResponseEntity.ok("login success");
//    }
}
