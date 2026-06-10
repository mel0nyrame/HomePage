package com.homepage.auth.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homepage.auth.admin.mapper.AdminMapper;
import com.homepage.auth.admin.service.AdminService;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.model.dto.AdminLoginDTO;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.model.entity.AdminEntity;
import com.homepage.common.util.RedisUtil;
import com.homepage.common.util.TokenService;
import com.homepage.common.web.ResponseCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private final RedisUtil redisUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager adminAuthenticationManager;
    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final TokenService tokenService;
    private final UserDetailsService adminUserDetailsService;

    public AdminServiceImpl(@Qualifier("adminAuthenticationManager") AuthenticationManager adminAuthenticationManager,
                            PasswordEncoder passwordEncoder,
                            RedisUtil redisUtil,
                            CompromisedPasswordChecker compromisedPasswordChecker,
                            TokenService tokenService,
                            @Qualifier("adminUserDetailsServiceImpl") UserDetailsService adminUserDetailsService
    ) {
        this.adminAuthenticationManager = adminAuthenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.redisUtil = redisUtil;
        this.compromisedPasswordChecker = compromisedPasswordChecker;
        this.tokenService = tokenService;
        this.adminUserDetailsService = adminUserDetailsService;
    }

    @Override
    public TokenDTO login(AdminLoginDTO adminLoginDTO) {
        // 验证验证码
        redisUtil.verifyCaptcha(adminLoginDTO);

        try {
            Authentication authentication = adminAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminLoginDTO.getAccount(), adminLoginDTO.getPassword())
            );
            return tokenService.issueTokenPair(authentication);
        } catch (BadCredentialsException e) {
            throw new BusinessException(ResponseCode.USER_PASSWORD_ERROR);
        } catch (DisabledException e) {
            throw new BusinessException(ResponseCode.USER_ACCOUNT_DISABLED);
        }
    }

    @Transactional
    @Override
    public void register(AdminRegisterDTO adminRegisterDTO) {
        // 验证验证码
        redisUtil.verifyCaptcha(adminRegisterDTO);

        // 查询密码是否泄漏
        CompromisedPasswordDecision passwordDecision = compromisedPasswordChecker.check(adminRegisterDTO.getPassword());
        if (passwordDecision.isCompromised()) {
            throw new BusinessException(ResponseCode.USER_PASSWORD_LEAKED);
        }

        // 查询管理员
        if (lambdaQuery().eq(AdminEntity::getAccount, adminRegisterDTO.getAccount()).exists()) {
            throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
        }

        AdminEntity admin = new AdminEntity();
        admin.setAccount(adminRegisterDTO.getAccount());
        admin.setNickname(adminRegisterDTO.getNickname());
        admin.setPassword(passwordEncoder.encode(adminRegisterDTO.getPassword()));
        admin.setEnabled(1);
        admin.setAuthorities("ADMIN");

        this.save(admin);
    }

    @Override
    public TokenDTO refreshToken(String refreshToken) {
        return tokenService.rotate(refreshToken, adminUserDetailsService);
    }

    @Override
    public void logout(String accessJti) {
        tokenService.revoke(accessJti);
    }

    @Override
    public long logoutAll(String account) {
        return tokenService.revokeAll(account);
    }
}
