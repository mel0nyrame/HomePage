package com.homepage.auth.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homepage.common.model.dto.AdminLoginDTO;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.common.model.entity.AdminEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.admin.service
 * @Date 5/25/26 21:44
 * @description: 管理员业务接口
 */
public interface AdminService extends UserDetailsService, IService<AdminEntity> {

    /**
     * 管理员登录
     *
     * @param adminLoginDTO 管理员登陆信息
     * @return JWT token
     */
    String login(AdminLoginDTO adminLoginDTO);

    /**
     * 管理员注册
     * @param adminRegisterDTO 管理员信息
     */
    void register(AdminRegisterDTO adminRegisterDTO);
}
