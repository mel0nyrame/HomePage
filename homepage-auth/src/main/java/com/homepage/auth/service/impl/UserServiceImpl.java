package com.homepage.auth.service.impl;

import com.homepage.common.util.JwtUtil;
import com.homepage.auth.model.dto.UserDTO;
import com.homepage.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.service.impl
 * @Date 5/23/26 01:48
 * @description: 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;

    public UserServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String token(UserDTO userDTO) {
        return jwtUtil.generateToken(userDTO.getUserId(), userDTO.getUsername(),  userDTO.getPassword());
    }
}
