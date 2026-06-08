package com.homepage.auth.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homepage.common.model.dto.EmailDTO;
import com.homepage.common.model.dto.LoginDTO;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.model.entity.UserEntity;
import org.springframework.security.core.Authentication;

/**
 * The interface User service.
 *
 * @Author Mel0ny
 * @Package com.homepage.auth.user.service
 * @Date 5/23/26 01:47
 * @description: 用户业务接口
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 用户登录
     * @param loginDTO 用户登陆信息
     * @return jwt生成的refresh_token和access_token
     */
    TokenDTO login(LoginDTO loginDTO);

    /**
     * 用户注册
     *
     * @param registerDTO 用户信息
     */
    void register(RegisterDTO registerDTO);

    /**
     * 通过验证码验证邮箱
     *
     * @param emailDTO 邮箱信息
     */
    void verifyEmail(EmailDTO emailDTO);

    /**
     * 重新发送验证码
     * @param email 邮箱
     */
    void retryEmail(String email);

    /**
     * 刷新refreshToken
     *
     * @param authentication 用户登陆信息
     * @return refreshToken
     */
    String refreshToken(Authentication authentication);
}
