package com.homepage.admin.initializer;

import com.homepage.admin.mapper.AdminMapper;
import com.homepage.admin.model.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author Mel0ny
 * @Package com.homepage.admin.initializer
 * @Date 5/25/26 21:55
 * @description: 管理员账号初始化
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminMapper adminMapper, PasswordEncoder passwordEncoder) {
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${app.admin.init-account}")
    private String initAccount;

    @Value("${app.admin.init-password}")
    private String initPassword;

    @Override
    public void run(String... args) {
        if (adminMapper.existsByAccount() > 0) {
            return;
        }
        AdminEntity admin = new AdminEntity();
        admin.setAccount(initAccount);
        admin.setPassword(passwordEncoder.encode(initPassword));
        admin.setEnabled(1);
        admin.setAuthorities("ADMIN");
        adminMapper.insertAdmin(admin);
    }
}
