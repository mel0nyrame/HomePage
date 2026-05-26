package com.homepage.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "用户登录请求")
public class LoginDTO {

    @NotBlank(message = "用户名或邮箱不能为空")
    @Schema(description = "用户名或邮箱", example = "zhangsan")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "用户密码", example = "abc123")
    private String password;
}
