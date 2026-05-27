package com.homepage.auth.captcha.service;

import com.homepage.auth.captcha.service.impl.CaptchaServiceImpl;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.captcha.service
 * @Date 5/28/26 01:01
 * @description: 验证码服务接口
 */
public interface CaptchaService {


    /**
     * 创建验证码的id和图片base64编码
     *
     * @return 验证码的id和图片base64编码
     */
    CaptchaServiceImpl.Captcha captcha();
}
