package com.weather.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.auth.AuthMapper;
import com.weather.auth.jwt.*;
import com.weather.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor /*의존성 주입(final의 생성자를 생성)*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTHENTICATION_URL = "/api/auth/login";

    private final JwtUtil jwtUtil;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final ObjectMapper objectMapper;
    private final AuthMapper authMapper;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

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
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                //.and()
                //.apply(new JwtSecurityConfig(jwtUtil));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(AUTHENTICATION_URL, objectMapper);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler()); // 로그인 성공시 실행되는 핸들러
        jwtAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler()); // 로그인 실패시 실행되는 핸들러
        jwtAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler(jwtUtil, objectMapper, authMapper);
    }

    @Bean
    public JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler() {
        return new JwtAuthenticationFailureHandler(objectMapper);
    }
}

/*
WebSecurityConfigurerAdapter 인터페이스의 구현체입니다.
Spring Security 의 가장 기본적인 설정이며 JWT 를 사용하지 않더라도 이 설정은 기본으로 들어갑니다.
오버라이드한 configure 내부에서 각종 설정들을 추가해줍니다.
*/
