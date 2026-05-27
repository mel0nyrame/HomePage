package com.homepage.auth.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.util.JwtUtil;
import com.homepage.common.web.ResponseCode;
import com.homepage.auth.user.mapper.UserMapper;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.model.entity.UserEntity;
import com.homepage.common.model.security.HomepageUserDetails;
import com.homepage.auth.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
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
 * @Package com.homepage.auth.user.service.impl
 * @Date 5/23/26 01:48
 * @description: 用户业务实现类
 */
@Primary
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           @Lazy @Qualifier("userAuthenticationManager") AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String account, String password) {
        boolean exists = lambdaQuery()
                .and(w -> w.eq(UserEntity::getUsername, account).or().eq(UserEntity::getEmail, account))
                .exists();
        if (!exists) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
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
    public void register(RegisterDTO registerDTO) {
        if (lambdaQuery().eq(UserEntity::getUsername, registerDTO.getUsername()).exists()) {
            throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
        }

        if (lambdaQuery().eq(UserEntity::getEmail, registerDTO.getEmail()).exists()) {
            throw new BusinessException(ResponseCode.USER_EMAIL_EXIST);
        }

        UserEntity user = new UserEntity();
        user.setNickname(registerDTO.getNickname());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        this.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserEntity user = lambdaQuery()
                .and(w -> w.eq(UserEntity::getUsername, account).or().eq(UserEntity::getEmail, account))
                .one();
        if (user == null) {
            throw new UsernameNotFoundException(account);
        }
        return new HomepageUserDetails(user);
    }
}
