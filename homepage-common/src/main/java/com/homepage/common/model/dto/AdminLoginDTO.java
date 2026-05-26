package com.homepage.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "管理员登录请求")
public class AdminLoginDTO {

    @NotBlank(message = "管理员账号不能为空")
    @Schema(description = "管理员账号", example = "admin")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "管理员密码", example = "admin123")
    private String password;
}
