package com.homepage.auth.captcha.controller;

import com.homepage.auth.captcha.service.CaptchaService;
import com.homepage.auth.captcha.service.impl.CaptchaServiceImpl;
import com.homepage.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "验证码接口", description = "获取图片验证码")
@RestController
@RequestMapping("api/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @PostMapping("/")
    @Operation(summary = "获取验证码base64图片", description = "获取验证码id和以base64编码的图片")
    public Response<CaptchaServiceImpl.Captcha> captcha() {
        CaptchaServiceImpl.Captcha captcha = captchaService.captcha();
        return Response.ok(captcha);
    }

}
