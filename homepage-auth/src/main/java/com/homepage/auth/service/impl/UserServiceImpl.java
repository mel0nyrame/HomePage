package com.homepage.auth.service.impl;

import com.homepage.auth.mapper.UserMapper;
import com.homepage.auth.model.dto.UserDTO;
import com.homepage.auth.model.entity.UserEntity;
import com.homepage.auth.service.UserService;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.util.JwtUtil;
import com.homepage.common.web.ResponseCode;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(JwtUtil jwtUtil, UserMapper userMapper,
                           PasswordEncoder passwordEncoder,
                           @Lazy AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String username, String password) {
        // TODO:若发送大量请求会导致数据库崩溃，等待处理

        // 查询用户是否存在
        if (userMapper.existsByUsername(username) == 0) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }

        try {
            // 传递和设置用户对象
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
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
    public void register(UserDTO userDTO) {
        // 查询用户名是否存在
        if (userMapper.existsByUsername(userDTO.getUsername()) > 0) {
            throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
        }

        // 设置用户对象
        UserEntity user = new UserEntity();
        user.setNickname(userDTO.getNickname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // 插入数据库
        userMapper.insertUser(user);
    }

    /**
     * 用户定位用户
     * @param username the username identifying the user whose data is required.
     * @return 用户对象
     * @throws UsernameNotFoundException 用户未找到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMapper.selectUser(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
