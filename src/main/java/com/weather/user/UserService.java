package com.weather.user;

import com.weather.user.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 회원 목록 조회
     * @return
     */
    List<User> getUserList();

    /**
     * 회원 등록
     * @param user
     */
    void insertUser(User user);

    int checkDuplicate(String loginId);
}
