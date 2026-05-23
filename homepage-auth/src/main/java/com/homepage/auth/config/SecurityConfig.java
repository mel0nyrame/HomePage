package com.homepage.auth.config;

import com.homepage.auth.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.config
 * @Date 5/22/26 23:05
 * @description: 配置Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf，因为认证采用jwt，无需cookie
                .csrf(AbstractHttpConfigurer::disable)
                // 无状态会话管理
                .sessionManagement((sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                // 设置请求路径强制认证
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/user/**").permitAll()
                        .anyRequest().authenticated()
                )
                // 在此之前添加过滤器
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
