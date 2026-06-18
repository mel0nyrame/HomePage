package com.homepage.auth.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homepage.common.model.dto.EmailDTO;
import com.homepage.common.model.dto.LoginDTO;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.model.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

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
     *
     * @param loginDTO 用户登陆信息
     * @param request  HTTP 请求
     * @return jwt 生成的 access_token 和 refresh_token
     */
    TokenDTO login(LoginDTO loginDTO, HttpServletRequest request);

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
     *
     * @param email 邮箱
     */
    void retryEmail(String email);

    /**
     * 用 refresh_token 换新的 (access_token, refresh_token) 对
     *
     * @param refreshToken 客户端传来的 refresh_token 字符串
     * @return 新的 token 对
     */
    TokenDTO refreshToken(String refreshToken);

    /**
     * 当前会话登出（吊销 access + refresh）
     *
     * @param accessJti 当前 access token 的 jti
     */
    void logout(String accessJti);

    /**
     * 吊销指定用户的所有会话（全部下线）
     *
     * @param username 用户名
     * @return 被吊销的会话数量
     */
    long logoutAll(String username);
}
