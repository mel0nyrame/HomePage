package com.homepage.auth.service;

import com.homepage.auth.model.dto.AdminLoginDTO;
import com.homepage.auth.model.dto.AdminRegisterDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.service
 * @Date 5/25/26 21:44
 * @description: 管理员业务接口
 */
public interface AdminService extends UserDetailsService {

    /**
     * 管理员登录
     *
     * @param account  管理员账号
     * @param password 密码
     * @return JWT token
     */
    String login(String account, String password);

    /**
     * 管理员注册
     * @param adminRegisterDTO 管理员信息
     */
    void register(AdminRegisterDTO adminRegisterDTO);
}
