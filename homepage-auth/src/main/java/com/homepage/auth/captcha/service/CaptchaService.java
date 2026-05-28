package com.homepage.auth.captcha.service;

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
    Captcha captcha();

    /**
     * 验证码数据载体
     *
     * @param captchaId 验证码id
     * @param image     验证码图片base64编码
     */
    record Captcha(String captchaId, String image) {}
}
