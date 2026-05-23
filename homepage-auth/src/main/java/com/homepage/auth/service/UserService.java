package com.homepage.auth.service;

import com.homepage.auth.model.dto.UserDTO;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.service
 * @Date 5/23/26 01:47
 * @description: 用户业务接口
 */
public interface UserService {

    String token(UserDTO userDTO);
}
