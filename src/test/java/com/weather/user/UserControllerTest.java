package com.weather.user;

import com.weather.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserList() {
        List<User> list = userService.getUserList();

        System.out.println(list);
    }
}