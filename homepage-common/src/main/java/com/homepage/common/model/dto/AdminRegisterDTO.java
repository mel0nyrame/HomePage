package com.homepage.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "管理员注册请求")
public class AdminRegisterDTO implements CaptchaAware {

    @NotBlank(message = "管理员昵称不能为空")
    @Size(min = 3, max = 20, message = "管理员昵称必须大于3小于20")
    @Schema(description = "管理员昵称", example = "张三", minLength = 3, maxLength = 20)
    private String nickname;

    @NotBlank(message = "管理员账号不能为空")
    @Size(min = 3, max = 20, message = "管理员账号必须大于3小于20")
    @Schema(description = "管理员账号", example = "new_admin", minLength = 3, maxLength = 20)
    private String account;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$",
            message = "密码必须由数字和字母组成，并且长度大于等于8个字符")
    @Schema(description = "管理员密码，必须包含数字和字母，长度>=8", example = "admin123456")
    private String password;

    @NotBlank(message = "验证码id不能空")
    @Schema(description = "验证码ID", example = "uuid")
    private String captchaID;

    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码", example = "7yagX")
    private String captcha;
}
