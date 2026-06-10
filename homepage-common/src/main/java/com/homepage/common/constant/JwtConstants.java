package com.homepage.common.constant;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.constant
 * @Date 5/23/26 00:59
 * @description: JWT 常量类
 */
public final class JwtConstants {

    private JwtConstants() {}

    /**
     * access_token 过期时间 30 分钟 * 60 秒 * 1000 毫秒
     */
    public static final Long JWT_ACCESS_TOKEN_EXPIRATION_TIME = 30 * 60 * 1000L;

    /**
     * refresh_token 过期时间 7 天 * 24 小时 * 60 分钟 * 60 秒 * 1000 毫秒
     */
    public static final Long JWT_REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L;
}
