package com.homepage.auth.admin.controller;

import com.homepage.common.model.dto.AdminLoginDTO;
import com.homepage.common.model.dto.AdminRegisterDTO;
import com.homepage.auth.admin.service.AdminService;
import com.homepage.common.web.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.admin.controller
 * @Date 5/25/26 21:32
 * @description: 管理员业务接口
 */
@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public Response<String> login(@Validated @RequestBody AdminLoginDTO adminLoginDTO) {
        String token = adminService.login(adminLoginDTO.getAccount(), adminLoginDTO.getPassword());
        return Response.ok(token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public Response<Void> register(@Validated @RequestBody AdminRegisterDTO adminRegisterDTO) {
        adminService.register(adminRegisterDTO);
        return Response.ok();
    }
}
