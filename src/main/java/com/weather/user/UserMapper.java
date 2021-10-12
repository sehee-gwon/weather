package com.weather.user;

import com.weather.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 회원 등록
     * @param user
     */
    void insertUser(User user);

    /**
     * 회원 정보 조회
     * @param loginId
     * @return
     */
    User getUserByLoginId(String loginId);
}
