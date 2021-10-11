package com.weather.auth.jwt;

import com.weather.common.enumeration.AuthorityType;
import com.weather.user.UserMapper;
import com.weather.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 회원 정보 조회, 존재하지 않을시 throw new exception
        User user = userMapper.getUserByLoginId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " 회원은 존재하지 않습니다.");
        }

        // User -> UserDetails 객체로 변환
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AuthorityType.ROLE_USER.toString());
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getUserId()), user.getPassword(), Collections.singleton(grantedAuthority));
    }
}
