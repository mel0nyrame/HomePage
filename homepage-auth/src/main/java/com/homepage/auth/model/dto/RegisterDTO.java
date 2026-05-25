package com.homepage.auth.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.model.dto
 * @Date 5/23/26 01:42
 * @description: 传输注册信息
 */
@Data
public class RegisterDTO {

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    @Size(min = 3,max = 20,message = "用户昵称必须大于3小于20")
    private String nickname;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3,max = 20,message = "用户名必须大于3小于20")
    private String username;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "输入必须是邮箱")
    private String email;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$",message = "密码必须由数字和字母组成，并且长度大于等于6个字符")
    private String password;
}
