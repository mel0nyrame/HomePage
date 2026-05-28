package com.homepage.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "用户注册请求")
public class RegisterDTO {

    @NotBlank(message = "用户昵称不能为空")
    @Size(min = 3, max = 20, message = "用户昵称必须大于3小于20")
    @Schema(description = "用户昵称", example = "张三", minLength = 3, maxLength = 20)
    private String nickname;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名必须大于3小于20")
    @Schema(description = "用户名", example = "zhangsan", minLength = 3, maxLength = 20)
    private String username;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "输入必须是邮箱")
    @Schema(description = "邮箱地址", example = "zhangsan@example.com")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$",
            message = "密码必须由数字和字母组成，并且长度大于等于6个字符")
    @Schema(description = "用户密码，必须包含数字和字母，长度>=6", example = "abc123")
    private String password;

    @NotBlank(message = "验证码id不能空")
    @Schema(description = "验证码ID", example = "uuid")
    private String captchaID;

    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码", example = "7yagX")
    private String captcha;
}
