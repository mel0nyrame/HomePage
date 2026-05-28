package com.homepage.common.model.dto;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/28/26 11:43
 * @description: 标记含有验证码字段的 DTO，供 RedisUtil.verifyCaptcha() 统一处理
 */
public interface CaptchaAware {
    String getCaptchaID();
    String getCaptcha();
}