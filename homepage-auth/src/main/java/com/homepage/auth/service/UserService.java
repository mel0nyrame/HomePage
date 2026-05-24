package com.homepage.auth.service;

import com.homepage.auth.model.dto.UserDTO;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.service
 * @Date 5/23/26 01:47
 * @description: 用户业务接口
 */
public interface UserService {

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
     * @param userDTO 用户信息
     */
    void register(UserDTO userDTO);

    // TODO: refresh token
}
