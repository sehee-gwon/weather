package com.weather.user;

import org.apache.ibatis.annotations.Mapper;
import com.weather.user.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
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
}