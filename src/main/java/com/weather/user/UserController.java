package com.weather.user;

import com.weather.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String join(User user) {
        userService.insertUser(user);
        return "redirect:/login.html";
    }
}
