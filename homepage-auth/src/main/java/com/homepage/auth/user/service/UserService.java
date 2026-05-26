package com.homepage.auth.user.service;

import com.homepage.common.model.dto.RegisterDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.user.service
 * @Date 5/23/26 01:47
 * @description: 用户业务接口
 */
public interface UserService extends UserDetailsService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return JWT token
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param registerDTO 用户信息
     */
    void register(RegisterDTO registerDTO);

    // TODO: refresh token
}
