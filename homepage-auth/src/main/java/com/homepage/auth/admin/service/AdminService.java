package com.homepage.auth.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homepage.common.model.dto.AdminLoginDTO;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.model.entity.AdminEntity;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.admin.service
 * @Date 5/25/26 21:44
 * @description: 管理员业务接口
 */
public interface AdminService extends IService<AdminEntity> {

    /**
     * 管理员登录
     *
     * @param adminLoginDTO 管理员登陆信息
     * @param request       HTTP 请求
     * @return 双 token
     */
    TokenDTO login(AdminLoginDTO adminLoginDTO, HttpServletRequest request);

    /**
     * 管理员注册
     * @param adminRegisterDTO 管理员信息
     */
    void register(AdminRegisterDTO adminRegisterDTO);

    /**
     * 用 refresh_token 换新的 (access_token, refresh_token) 对
     */
    TokenDTO refreshToken(String refreshToken);

    /**
     * 当前会话登出
     */
    void logout(String accessJti);

    /**
     * 吊销管理员的所有会话
     */
    long logoutAll(String account);
}
