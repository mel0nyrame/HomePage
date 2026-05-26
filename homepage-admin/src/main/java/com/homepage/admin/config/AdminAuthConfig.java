package com.homepage.admin.config;

import com.homepage.admin.service.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Mel0ny
 * @Package com.homepage.admin.config
 * @Date 5/26/26 09:17
 * @description: 管理员认证管理器配置
 */
@Configuration
public class AdminAuthConfig {

    @Bean("adminAuthenticationManager")
    public AuthenticationManager adminAuthenticationManager(AdminService adminDetailsService,
                                                            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(adminDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }
}
