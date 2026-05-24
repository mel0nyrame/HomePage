package com.homepage.common.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static com.homepage.common.constant.JwtConstants.JWT_SECRET;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.config
 * @Date 5/24/26 14:31
 * @description: jwt加解密配置
 */
@Configuration
public class JwtConfig {

    @Bean
    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(
                JWT_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"
        );
        JWKSource<SecurityContext> jwks = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(JWT_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256")).build();
    }
}
