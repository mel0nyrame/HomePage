package com.homepage.auth.model.dto;

import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.model.dto
 * @Date 5/23/26 01:42
 * @description: 传输用户信息
 */
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
}
