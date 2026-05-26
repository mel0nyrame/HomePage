package com.homepage.user.service.impl;

import com.homepage.common.exception.BusinessException;
import com.homepage.common.util.JwtUtil;
import com.homepage.common.web.ResponseCode;
import com.homepage.user.mapper.UserMapper;
import com.homepage.auth.model.dto.RegisterDTO;
import com.homepage.user.model.entity.UserEntity;
import com.homepage.user.service.UserService;
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
 * @Package com.homepage.user.service.impl
 * @Date 5/23/26 01:48
 * @description: 用户业务实现类
 */
@Primary
@Service
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(JwtUtil jwtUtil, UserMapper userMapper,
                           PasswordEncoder passwordEncoder,
                           @Lazy @Qualifier("userAuthenticationManager") AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String account, String password) {
        // TODO:若发送大量请求会导致数据库崩溃，等待处理

        boolean exists = userMapper.selectUser(account) != null
                || userMapper.selectUserByEmail(account) != null;
        if (!exists) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }

        try {
            // 传递和设置用户对象
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account, password)
            );
            // 返回验证token
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
        // 查询用户名是否存在
        if (userMapper.existsByUsername(registerDTO.getUsername()) > 0) {
            throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
        }

        // 查询邮箱是否存在
        if (userMapper.existsByEmail(registerDTO.getEmail()) > 0) {
            throw new BusinessException(ResponseCode.USER_EMAIL_EXIST);
        }

        // 设置用户对象
        UserEntity user = new UserEntity();
        user.setNickname(registerDTO.getNickname());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // 插入数据库
        userMapper.insertUser(user);
    }

    /**
     * 用户定位用户
     * @param account the username identifying the user whose data is required.
     * @return 用户对象
     * @throws UsernameNotFoundException 用户未找到
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserEntity user = userMapper.selectUser(account);
        if (user == null) {
            user = userMapper.selectUserByEmail(account);
        }
        if (user == null) {
            throw new UsernameNotFoundException(account);
        }
        return user;
    }

}
