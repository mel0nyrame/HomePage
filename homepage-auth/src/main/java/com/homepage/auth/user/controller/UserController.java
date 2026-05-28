package com.homepage.auth.user.controller;

import com.homepage.auth.user.service.UserService;
import com.homepage.common.model.dto.EmailDTO;
import com.homepage.common.model.dto.LoginDTO;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Response<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        return Response.ok(token);
    }

    @Operation(summary = "用户注册", description = "注册新用户账号")
    @PostMapping("/register")
    public Response<Void> register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Response.ok();
    }

    @GetMapping("/email")
    @Operation(summary = "验证邮箱", description = "验证邮箱验证码")
    public Response<Void> verifyEmail(@RequestBody EmailDTO emailDTO) {
        userService.verifyEmail(emailDTO);
        return Response.ok();
    }
}
