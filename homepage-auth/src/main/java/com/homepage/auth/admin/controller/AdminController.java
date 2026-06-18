package com.homepage.auth.admin.controller;

import com.homepage.auth.admin.service.AdminService;
import com.homepage.common.model.dto.AdminLoginDTO;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理员接口", description = "管理员登录与注册相关接口")
@RestController
@RequestMapping("api/admin")
public class AdminController {

    private static final String REFRESH_TOKEN_HEADER = "X-Refresh-Token";

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "管理员登录", description = "使用管理员账号和密码登录，返回 JWT 双令牌")
    @PostMapping("/login")
    public Response<TokenDTO> login(@Validated @RequestBody AdminLoginDTO adminLoginDTO, HttpServletRequest request) {
        return Response.ok(adminService.login(adminLoginDTO, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "管理员注册", description = "注册新管理员账号，需要管理员权限",
            security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/register")
    public Response<Void> register(@Validated @RequestBody AdminRegisterDTO adminRegisterDTO) {
        adminService.register(adminRegisterDTO);
        return Response.ok();
    }

    @Operation(summary = "管理员刷新 token", description = "用 X-Refresh-Token 头里的 refresh_token 换新的 (access_token, refresh_token) 对")
    @PostMapping("/refresh")
    public Response<TokenDTO> refreshToken(@RequestHeader(REFRESH_TOKEN_HEADER) @NotBlank String refreshToken) {
        return Response.ok(adminService.refreshToken(refreshToken));
    }

    @Operation(summary = "管理员登出", description = "吊销当前会话")
    @PostMapping("/logout")
    public Response<Void> logout(Authentication authentication) {
        String jti = authentication instanceof Jwt jwt ? jwt.getId() : null;
        adminService.logout(jti);
        return Response.ok();
    }

    @Operation(summary = "管理员全部下线", description = "吊销该管理员账号的所有会话")
    @PostMapping("/logout/all")
    public Response<Long> logoutAll(Authentication authentication) {
        return Response.ok(adminService.logoutAll(authentication.getName()));
    }
}
