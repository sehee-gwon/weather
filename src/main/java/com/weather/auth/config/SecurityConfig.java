package com.weather.auth.config;

import com.weather.auth.jwt.JwtAccessDeniedHandler;
import com.weather.auth.jwt.JwtAuthenticationEntryPoint;
import com.weather.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

//    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                // customizing exception handling 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // security 세션 설정을 stateless 로 설정 (http session 생성하지 않음)
                // always: 항상 생성, if_required: 필요 시 생성 (default), never: 생성하지 않지만 이미 존재하면 사용, stateless: 생성하지 않고 존재해도 사용하지 않음
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                 // /api/board/** 경로는 인증된 사용자의 접근을 허용, 그 외 경로는 모든 사용자의 접근을 허용
                .and()
                .authorizeRequests()
                .antMatchers("/api/board/**").authenticated()
                .anyRequest().permitAll()

                // customizing filter 추가
                .and()
                .apply(new JwtSecurityConfig(jwtProvider));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
}
