package com.homepage.admin.service.impl;

import com.homepage.admin.mapper.AdminMapper;
import com.homepage.auth.model.dto.AdminLoginDTO;
import com.homepage.auth.model.dto.AdminRegisterDTO;
import com.homepage.admin.model.entity.AdminEntity;
import com.homepage.admin.service.AdminService;
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
 * @Package com.homepage.admin.service.impl
 * @Date 5/25/26 21:44
 * @description: 管理员业务实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager adminAuthenticationManager;

    public AdminServiceImpl(JwtUtil jwtUtil,
                            @Lazy @Qualifier("adminAuthenticationManager") AuthenticationManager adminAuthenticationManager,
                            AdminMapper adminMapper,
                            PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.adminAuthenticationManager = adminAuthenticationManager;
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String account, String password) {
        // 查询管理员是否存在
        if (adminMapper.selectByAccount(account) == null) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }

        try {
            // 传递和设置管理员对象
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
        // 查询账号是否存在
        if (adminMapper.existsByUsername(adminRegisterDTO.getAccount()) > 0) {
            throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
        }

        // 设置管理员账号
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAccount(adminRegisterDTO.getAccount());
        adminEntity.setPassword(passwordEncoder.encode(adminRegisterDTO.getPassword()));

        // 插入数据库
        adminMapper.insertAdmin(adminEntity);
    }

    /**
     * 用于定位管理员
     * @param account the 管理员账号 identifying the user whose data is required.
     * @return 管理员对象
     * @throws UsernameNotFoundException 管理员未找到
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        AdminEntity admin = adminMapper.selectByAccount(account);
        if (admin == null) {
            throw new UsernameNotFoundException("管理员不存在: " + account);
        }
        return admin;
    }
}
