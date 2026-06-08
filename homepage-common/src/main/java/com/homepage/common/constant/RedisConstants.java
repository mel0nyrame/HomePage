package com.homepage.common.constant;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.constant
 * @Date 5/28/26 02:15
 * @description: Redis常量类
 */
public final class RedisConstants {

    // 防止实例化
    private RedisConstants() {}

    /**
     * 图片验证码前缀
     */
    public final static String REDIS_AUTH_CAPTCHA_PREFIX = "auth:captcha:";

    /**
     * 邮箱验证码前缀
     */
    public final static String REDIS_EMAIL_CAPTCHA_PREFIX = "email:captcha:";

    /**
     * 用户信息前缀
     */
    public final static String REDIS_USER_PREFIX = "user:";

    /**
     * 登陆refreshToken前缀
     */
    public final static String REDIS_TOKEN_REFRESH_PREFIX = "token:refresh:";

    /**
     * 登陆accessToken前缀
     */
    public final static String REDIS_TOKEN_ACCESS_PREFIX = "token:access:";
}
