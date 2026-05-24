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

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
