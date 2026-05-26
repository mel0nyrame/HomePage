package com.homepage.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.dto
 * @Date 5/25/26 22:03
 * @description: 传递添加管理员信息
 */
@Data
public class AdminRegisterDTO {
    /**
     * 管理员账号
     */
    @NotBlank(message = "管理员账号不能为空")
    @Size(min = 3,max = 20,message = "管理员账号必须大于3小于20")
    private String account;

    /**
     * 管理员密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$",message = "密码必须由数字和字母组成，并且长度大于等于8个字符")
    private String password;
}
