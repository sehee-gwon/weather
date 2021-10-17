package com.weather.auth.config;

import com.weather.auth.jwt.JwtAccessDeniedHandler;
import com.weather.auth.jwt.JwtAuthenticationEntryPoint;
import com.weather.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor /*의존성 주입(final의 생성자를 생성)*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                // /api/open-weather/** 경로만 인증된 유저 허용
                .antMatchers("/api/open-weather/**").authenticated()
                // 그 외 경로는 모든 유저 허용
                .anyRequest().permitAll()

                // exception handling 할 때 우리가 만든 클래스를 추가
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .and()
                .apply(new JwtSecurityConfig(jwtProvider));
    }
}

/*
WebSecurityConfigurerAdapter 인터페이스의 구현체입니다.
Spring Security 의 가장 기본적인 설정이며 JWT 를 사용하지 않더라도 이 설정은 기본으로 들어갑니다.
오버라이드한 configure 내부에서 각종 설정들을 추가해줍니다.
*/
