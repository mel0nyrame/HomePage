package com.homepage.common.constant;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.constant
 * @Date 5/28/26 02:15
 * @description: Redis 常量类
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
     * refresh token 存储 key 前缀：token:refresh:{jti} -> refreshToken 字符串
     */
    public final static String REDIS_TOKEN_REFRESH_PREFIX = "token:refresh:";

    /**
     * access token 存储 key 前缀：token:access:{jti} -> accessToken 字符串
     */
    public final static String REDIS_TOKEN_ACCESS_PREFIX = "token:access:";

    /**
     * 用户 refresh 会话集合 key 前缀：token:user:refresh:{username} -> Set<jti>
     * 用于按用户名一键踢下线
     */
    public final static String REDIS_USER_REFRESH_SET_PREFIX = "token:user:refresh:";

    /**
     * refresh jti -> username 索引哈希：token:refresh:index（Hash）
     * 用于 refresh 接口根据 jti 反查 username，进而加载 UserDetails 重新签发
     */
    public final static String REDIS_REFRESH_INDEX = "token:refresh:index";
}
