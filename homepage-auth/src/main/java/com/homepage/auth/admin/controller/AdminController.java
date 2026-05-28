package com.homepage.auth.admin.controller;

import com.homepage.auth.admin.service.AdminService;
import com.homepage.common.model.dto.AdminLoginDTO;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理员接口", description = "管理员登录与注册相关接口")
@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "管理员登录", description = "使用管理员账号和密码登录，返回JWT令牌")
    @PostMapping("/login")
    public Response<String> login(@Validated @RequestBody AdminLoginDTO adminLoginDTO) {
        String token = adminService.login(adminLoginDTO);
        return Response.ok(token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "管理员注册", description = "注册新管理员账号，需要管理员权限",
            security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/register")
    public Response<Void> register(@Validated @RequestBody AdminRegisterDTO adminRegisterDTO) {
        adminService.register(adminRegisterDTO);
        return Response.ok();
    }
}
