package com.homepage.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.dto
 * @Date 5/28/26 22:19
 * @description: 用于传输邮箱信息
 */
@Data
public class EmailDTO {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "输入必须是邮箱")
    @Schema(description = "邮箱地址", example = "zhangsan@example.com")
    private String email;

    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码", example = "7yagxX")
    @Size(min = 6, max = 6, message = "验证码必须为6位")
    private String captcha;
}
