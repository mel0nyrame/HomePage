package com.homepage.common.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

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
        OctetSequenceKey jwk = new OctetSequenceKey.Builder(JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .algorithm(JWSAlgorithm.HS256)
                .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(JWT_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256")).build();
    }
}
