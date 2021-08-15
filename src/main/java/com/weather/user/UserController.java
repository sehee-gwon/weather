package com.weather.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weather.user.domain.User;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info(Model model) {
        List<User> list = userService.getUserList();
        model.addAttribute("list", list);
        return "OK";
    }

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        userService.insertUser(user);
        return "OK";
    }
}
