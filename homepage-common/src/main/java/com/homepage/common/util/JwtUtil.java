package com.homepage.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
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

    public final JwtEncoder encoder;

    public final JwtDecoder decoder;

    public JwtUtil(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.encoder = jwtEncoder;
        this.decoder = jwtDecoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("homepage")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(JWT_TOKEN_EXPIRATION_TIME))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private Jwt decode(String token) throws JwtException {
        return this.decoder.decode(token);
    }

    public String getUsername(String token) {
        return decode(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return decode(token).getExpiresAt().isBefore(Instant.now());
    }
}
