package com.weather.user;

import com.weather.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public void insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
    }

    @Override
    public int checkDuplicate(String loginId) {
        return userMapper.checkDuplicate(loginId);
    }

}
