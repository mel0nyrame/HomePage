package com.homepage.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.dto
 * @Date 5/25/26 20:33
 * @description: 传递登陆信息
 */
@Data
public class LoginDTO {

    /**
     * 用户账号，可以是用户名或邮箱
     */
    @NotBlank(message = "用户名或邮箱不能为空")
    private String account;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
