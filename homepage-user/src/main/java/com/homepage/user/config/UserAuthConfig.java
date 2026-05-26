package com.homepage.user.config;

import com.homepage.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.config
 * @Date 5/26/26 09:17
 * @description: 用户认证管理器配置
 */
@Configuration
public class UserAuthConfig {

    @Primary
    @Bean("userAuthenticationManager")
    public AuthenticationManager userAuthenticationManager(UserService userDetailsService,
                                                           PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }
}
