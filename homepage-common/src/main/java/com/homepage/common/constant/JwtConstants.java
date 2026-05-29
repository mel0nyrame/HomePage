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
     * token过期时间 2天 * 24小时 * 60分钟 * 60 秒 * 1000毫秒
     */
    public static final Long JWT_TOKEN_EXPIRATION_TIME = 2 * 24 * 60 * 60 * 1000L;
}
