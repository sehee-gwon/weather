package com.weather.user;

import com.weather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 목록 조회
     * @return
     */
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    /**
     * 회원 등록
     * @param user
     */
    public void insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
    }

    /**
     * 회원정보 조회
     * @param loginId
     * @return
     */
    public User getUserByLoginId(String loginId) {
        return userMapper.getUserByLoginId(loginId);
    }
}
