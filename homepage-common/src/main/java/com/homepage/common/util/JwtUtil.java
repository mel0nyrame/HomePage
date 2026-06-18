package com.homepage.common.util;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

import static com.homepage.common.constant.JwtConstants.JWT_ACCESS_TOKEN_EXPIRATION_TIME;
import static com.homepage.common.constant.JwtConstants.JWT_REFRESH_TOKEN_EXPIRATION_TIME;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/23/26 00:53
 * @description: JWT 工具类。统一签发 access / refresh token，包含 jti 与 type 声明。
 */
@Component
public class JwtUtil {

    /**
     * access token 在 JWT claims 中的 type 字段值
     */
    public static final String TYPE_ACCESS = "access_token";

    /**
     * refresh token 在 JWT claims 中的 type 字段值
     */
    public static final String TYPE_REFRESH = "refresh_token";

    private final JwtEncoder encoder;

    public JwtUtil(JwtEncoder jwtEncoder) {
        this.encoder = jwtEncoder;
    }

    /**
     * 生成刷新 token
     *
     * @param authentication 验证对象
     * @return 刷新 token 字符串
     */
    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication, TYPE_REFRESH, JWT_REFRESH_TOKEN_EXPIRATION_TIME);
    }

    /**
     * 生成访问 token
     *
     * @param authentication 验证对象
     * @return 访问 token 字符串
     */
    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, TYPE_ACCESS, JWT_ACCESS_TOKEN_EXPIRATION_TIME);
    }

    /**
     * 生成 token 核心方法
     *
     * @param authentication 验证对象
     * @param type           token 类型（access_token / refresh_token）
     * @param expiration     过期毫秒数
     * @return token 字符串
     */
    public String generateToken(Authentication authentication, String type, Long expiration) {
        Instant now = Instant.now();
        String scope = CollUtil.join(authentication.getAuthorities(), " ", GrantedAuthority::getAuthority);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("homepage")
                .issuedAt(now)
                .expiresAt(now.plusMillis(expiration))
                .subject(authentication.getName())
                .id(UUID.randomUUID().toString())
                .claim("scope", scope)
                .claim("type", type)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        return this.encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * 解码 token 拿到 claims
     *
     * @param token   原始 token 字符串
     * @param decoder Spring 注入的 JwtDecoder
     * @return 解析后的 Jwt
     */
    public Jwt decode(String token, JwtDecoder decoder) {
        return decoder.decode(token);
    }
}
