package com.homepage.auth.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homepage.auth.admin.mapper.AdminMapper;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.common.model.entity.AdminEntity;
import com.homepage.common.model.security.AdminUserDetails;
import com.homepage.auth.admin.service.AdminService;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.util.JwtUtil;
import com.homepage.common.web.ResponseCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.admin.service.impl
 * @Date 5/25/26 21:44
 * @description: 管理员业务实现类
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager adminAuthenticationManager;

    public AdminServiceImpl(JwtUtil jwtUtil,
                            @Lazy @Qualifier("adminAuthenticationManager") AuthenticationManager adminAuthenticationManager,
                            PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.adminAuthenticationManager = adminAuthenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String account, String password) {
        if (lambdaQuery().eq(AdminEntity::getAccount, account).one() == null) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }

        try {
            Authentication authentication = adminAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account, password)
            );
            return jwtUtil.generateToken(authentication);
        } catch (BadCredentialsException e) {
            throw new BusinessException(ResponseCode.USER_PASSWORD_ERROR);
        } catch (DisabledException e) {
            throw new BusinessException(ResponseCode.USER_ACCOUNT_DISABLED);
        }
    }

    @Transactional
    @Override
    public void register(AdminRegisterDTO adminRegisterDTO) {
        if (lambdaQuery().eq(AdminEntity::getAccount, adminRegisterDTO.getAccount()).exists()) {
            throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
        }

        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAccount(adminRegisterDTO.getAccount());
        adminEntity.setNickname(adminRegisterDTO.getNickname());
        adminEntity.setPassword(passwordEncoder.encode(adminRegisterDTO.getPassword()));

        this.save(adminEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        AdminEntity admin = lambdaQuery().eq(AdminEntity::getAccount, account).one();
        if (admin == null) {
            throw new UsernameNotFoundException("管理员不存在: " + account);
        }
        return new AdminUserDetails(admin);
    }
}
