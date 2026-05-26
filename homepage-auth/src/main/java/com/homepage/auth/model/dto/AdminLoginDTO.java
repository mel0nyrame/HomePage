package com.homepage.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.admin.model.dto
 * @Date 5/25/26 21:22
 * @description: 传递管理员登录信息
 */
@Data
public class AdminLoginDTO {

    /**
     * 管理员账号
     */
    @NotBlank(message = "管理员账号不能为空")
    private String account;

    /**
     * 管理员密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
