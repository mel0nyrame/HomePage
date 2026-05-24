package com.homepage.auth.controller;

import com.homepage.auth.service.UserService;
import com.homepage.common.web.Response;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Mel0ny
 * @Package com.homepage.user.controller
 * @Date 5/23/26 01:43
 * @description: 用户业务接口
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/token")
    public Response<String> token(Authentication authentication) {
        return Response.ok(userService.token(authentication));
    }
}
