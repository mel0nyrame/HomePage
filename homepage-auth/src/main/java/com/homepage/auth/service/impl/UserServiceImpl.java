package com.homepage.auth.service.impl;

import com.homepage.auth.service.UserService;
import com.homepage.common.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.service.impl
 * @Date 5/23/26 01:48
 * @description: 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;

    public UserServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * 获取token
     * @param userDTO 用户数据
     * @return token
     */
    @Override
    public String token(Authentication authentication) {
        return jwtUtil.generateToken(authentication);
    }
}
