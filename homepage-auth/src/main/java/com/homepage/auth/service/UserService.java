package com.homepage.auth.service;

import org.springframework.security.core.Authentication;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.service
 * @Date 5/23/26 01:47
 * @description: 用户业务接口
 */
public interface UserService {

    /**
     * 获取token
     * @param authentication 用户数据
     * @return token
     */
    String token(Authentication authentication);
}
