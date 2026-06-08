package com.homepage.common.constant;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.constant
 * @Date 5/23/26 00:59
 * @description: JWT常量类
 */
public final class JwtConstants {

    private JwtConstants() {}

    /**
     * access_token过期时间 7天 * 24小时 * 60分钟 * 60 秒 * 1000毫秒
     */
    public static final Long JWT_ACCESS_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L;

    /**
     * refresh_token过期时间 15分钟 * 60 秒 * 1000毫秒
     */
    public static final Long JWT_REFRESH_TOKEN_EXPIRATION_TIME = 15 * 60 * 1000L;
}
