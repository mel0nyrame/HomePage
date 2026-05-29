package com.homepage.common.constant;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.constant
 * @Date 5/23/26 00:59
 * @description: JWT常量类
 */
@Component
public class JwtConstants {

    /**
     * token过期时间 2天 * 24小时 * 60分钟 * 60 秒 * 1000毫秒
     */
    public static final Long JWT_TOKEN_EXPIRATION_TIME = 2 * 24 * 60 * 60 * 1000L;
    /**
     * JWT算法密钥
     */
    public static String JWT_SECRET;

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        JWT_SECRET = secret;
    }
}
