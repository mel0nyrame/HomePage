package com.homepage.auth.user.controller;

import com.homepage.auth.user.service.UserService;
import com.homepage.common.model.dto.EmailDTO;
import com.homepage.common.model.dto.LoginDTO;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口", description = "用户登录与注册相关接口")
@RestController
@RequestMapping("api/user")
public class UserController {

    private static final String REFRESH_TOKEN_HEADER = "X-Refresh-Token";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "用户登录", description = "使用用户名/邮箱和密码登录，返回 JWT 双令牌")
    @PostMapping("/login")
    public Response<TokenDTO> login(@Validated @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return Response.ok(userService.login(loginDTO, request));
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

    @Operation(summary = "刷新 token", description = "用 X-Refresh-Token 头里的 refresh_token 换新的 (access_token, refresh_token) 对")
    @PostMapping("/refresh")
    public Response<TokenDTO> refreshToken(@RequestHeader(REFRESH_TOKEN_HEADER) @NotBlank String refreshToken) {
        return Response.ok(userService.refreshToken(refreshToken));
    }

    @Operation(summary = "当前会话登出", description = "吊销当前 access_token 对应的会话（含 refresh_token）")
    @PostMapping("/logout")
    public Response<Void> logout(Authentication authentication) {
        String jti = authentication instanceof Jwt jwt ? jwt.getId() : null;
        userService.logout(jti);
        return Response.ok();
    }

    @Operation(summary = "全部下线", description = "吊销当前用户的所有会话")
    @PostMapping("/logout/all")
    public Response<Long> logoutAll(Authentication authentication) {
        Long count = userService.logoutAll(authentication.getName());
        return Response.ok(count);
    }
}
