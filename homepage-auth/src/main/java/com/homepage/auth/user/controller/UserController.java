package com.homepage.auth.user.controller;

import com.homepage.auth.user.service.UserService;
import com.homepage.common.model.dto.EmailDTO;
import com.homepage.common.model.dto.LoginDTO;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户接口", description = "用户登录与注册相关接口")
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "用户登录", description = "使用用户名/邮箱和密码登录，返回JWT令牌")
    @PostMapping("/login")
    public Response<TokenDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
        TokenDTO token = userService.login(loginDTO);
        return Response.ok(token);
    }

    @Operation(summary = "用户注册", description = "注册新用户账号")
    @PostMapping("/register")
    public Response<Void> register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Response.ok();
    }

    @PostMapping("/email/verify")
    @Operation(summary = "验证邮箱", description = "验证邮箱验证码")
    public Response<Void> verifyEmail(@Validated @RequestBody EmailDTO emailDTO) {
        userService.verifyEmail(emailDTO);
        return Response.ok();
    }

    @PostMapping("/email/retry")
    @Operation(summary = "重新发送验证码", description = "当验证码过期之后，重新发送验证码")
    public Response<Void> retryEmail(@Validated @RequestBody EmailDTO emailDTO) {
        userService.retryEmail(emailDTO.getEmail());
        return Response.ok();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/refresh")
    @Operation(summary = "刷新token", description = "当accessToken未过期时生成新的refreshToken")
    public Response<String> refreshToken(Authentication authentication) {
        String refreshToken = userService.refreshToken(authentication);
        return Response.ok(refreshToken);
    }
}
