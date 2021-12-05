package com.weather.user;

import com.weather.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

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
