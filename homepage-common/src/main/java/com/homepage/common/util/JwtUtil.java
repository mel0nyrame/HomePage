package com.homepage.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.homepage.common.constant.JwtConstants.*;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/23/26 00:53
 * @description: jwt工具类
 */
@Component
public class JwtUtil {

    public String generateToken(Long userId,String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        return JWT.create()
                .withClaim("userId",userId)
                .withClaim("username",username)
                .withClaim("password",password)
                .withIssuer("homepage")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_TIME))
                .sign(algorithm);
    }

    public DecodedJWT verifier(String token) {
        return JWT.require(Algorithm.HMAC256(JWT_SECRET)).build().verify(token);
    }

    public Long getUserId(String token) {
        return verifier(token).getClaim("userId").asLong();
    }

    public String getUsername(String token) {
        return verifier(token).getClaim("username").asString();
    }

    public String getPassword(String token) {
        return verifier(token).getClaim("password").asString();
    }

    public boolean isTokenExpired(String token) {
        return verifier(token).getExpiresAt().before(new Date());
    }
}
