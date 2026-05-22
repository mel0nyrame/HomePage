package com.homepage.user.controller;

import com.homepage.common.web.Response;
import com.homepage.user.model.dto.UserDTO;
import com.homepage.user.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    public Response<String> token(@RequestBody UserDTO userDTO) {
        return Response.ok(userService.token(userDTO));
    }
}
