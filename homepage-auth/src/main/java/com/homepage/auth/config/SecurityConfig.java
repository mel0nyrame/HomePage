package com.homepage.auth.config;

import com.homepage.auth.admin.service.impl.AdminUserDetailsServiceImpl;
import com.homepage.auth.user.service.impl.UserDetailsServiceImpl;
import com.homepage.common.exception.RestAccessDeniedHandler;
import com.homepage.common.exception.RestAuthenticationEntryPoint;
import com.homepage.common.security.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

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

    private final RestAuthenticationEntryPoint  restAuthenticationEntryPoint;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final JwtValidationFilter jwtValidationFilter;

    public SecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint
            , RestAccessDeniedHandler restAccessDeniedHandler
            , JwtValidationFilter jwtValidationFilter) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.jwtValidationFilter = jwtValidationFilter;
    }

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
                // 会话管理，设置绝不会从HttpSession中获取会话
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 启用支持JWT编码的持有令牌支持
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                // 自定义校验：在 Spring Security 默认 BearerToken 过滤器之后，
                // 再校验 type=access_token 与 jti 在 Redis 中存在（防 refresh_token 误用、防吊销）
                .addFilterAfter(jwtValidationFilter, BearerTokenAuthenticationFilter.class)
                // 错误处理
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(restAuthenticationEntryPoint)
                        .accessDeniedHandler(restAccessDeniedHandler)
                )
                // 请求头配置
                .headers(headers -> headers
                        // 禁止页面被任何网站通过<frame>、<iframe>、<embed>或<object>嵌入
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                        // 禁止浏览器进行 MIME 类型嗅探
                        .contentTypeOptions(Customizer.withDefaults())
                        // 强制使用https，包括子域名
                        .httpStrictTransportSecurity(hsts -> hsts
                        .includeSubDomains(true)
                        .maxAgeInSeconds(31536000))
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

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
