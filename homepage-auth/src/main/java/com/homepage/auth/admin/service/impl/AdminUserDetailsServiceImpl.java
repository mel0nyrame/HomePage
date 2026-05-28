package com.homepage.auth.admin.service.impl;

import com.homepage.auth.admin.service.AdminService;
import com.homepage.common.model.entity.AdminEntity;
import com.homepage.common.model.security.AdminUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.admin.service.impl
 * @Date 5/29/26 01:06
 * @description: 独立的管理员认证服务
 */
@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    private final AdminService adminService;

    public AdminUserDetailsServiceImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        AdminEntity admin = adminService.lambdaQuery().eq(AdminEntity::getAccount, account).one();
        if (admin == null) {
            throw new UsernameNotFoundException("管理员不存在: " + account);
        }
        return new AdminUserDetails(admin);
    }
}
