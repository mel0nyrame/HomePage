package com.homepage.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.homepage.common.constant.JwtConstants.JWT_TOKEN_EXPIRATION_TIME;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/23/26 00:53
 * @description: jwt工具类
 */
@Component
public class JwtUtil {

    private final JwtEncoder encoder;

    private final JwtDecoder decoder;

    public JwtUtil(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.encoder = jwtEncoder;
        this.decoder = jwtDecoder;
    }

    /**
     * 生成token
     * @param authentication 验证对象
     * @return token
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("homepage")
                .issuedAt(now)
                .expiresAt(now.plusMillis(JWT_TOKEN_EXPIRATION_TIME))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        return this.encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    private Jwt decode(String token) throws JwtException {
        return this.decoder.decode(token);
    }

    /**
     * 根据token获取用户名
     * @param token token
     * @return 用户名
     */
    public String getUsername(String token) {
        return decode(token).getSubject();
    }

    /**
     * 判断token是否过期
     * @param token token
     * @return 布尔值
     */
    public boolean isTokenExpired(String token) {
        return Objects.requireNonNull(decode(token).getExpiresAt()).isBefore(Instant.now());
    }
}
