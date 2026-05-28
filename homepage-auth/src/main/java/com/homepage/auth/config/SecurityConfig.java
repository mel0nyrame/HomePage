package com.homepage.auth.config;

import com.homepage.auth.admin.service.impl.AdminUserDetailsServiceImpl;
import com.homepage.auth.user.service.impl.UserDetailsServiceImpl;
import com.homepage.common.exception.RestAccessDeniedHandler;
import com.homepage.common.exception.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.config
 * @Date 5/22/26 23:05
 * @description: 配置Spring Security，包含用户和管理员认证管理器
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] requestMatchers = {
            "/api/user/login",
            "/api/user/register",
            "/api/user/email/verify",
            "/api/user/email/retry",
            "/api/admin/login",
            "/api/captcha/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs/**",
            "/api-docs",
            "/actuator/health"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        http
                // 关闭csrf保护，因为认证方式是jwt
                .csrf(AbstractHttpConfigurer::disable)
                // 会话管理，设置绝不会从HttpSession中获取回话
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 启用支持JWT编码的持有令牌支持
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                // 错误处理
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler())
                )
                // 设置认证请求配置
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(requestMatchers).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Primary
    @Bean("userAuthenticationManager")
    public AuthenticationManager userAuthenticationManager(UserDetailsServiceImpl userDetailsService,
                                                           PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    @Bean("adminAuthenticationManager")
    public AuthenticationManager adminAuthenticationManager(AdminUserDetailsServiceImpl adminDetailsService,
                                                            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(adminDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }
}
