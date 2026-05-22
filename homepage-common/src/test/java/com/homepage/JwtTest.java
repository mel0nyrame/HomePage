package com.homepage;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @Author Mel0ny
 * @Package com.homepage
 * @Date 5/23/26 00:33
 * @description: 测试jwt
 */
@SpringBootTest(classes = SpringSecurityTest.class)
public class JwtTest {

    @Test
    public void test() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("mel0ny")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000))
                .withAudience("app")
                .withClaim("username", "mel0ny")
                .withClaim("password", "123")
                .withClaim("role", "admin")
                .sign(algorithm);

        System.out.println(token);

        DecodedJWT decode = JWT.decode(token);
        System.out.println("subject: " + decode.getSubject() + " " + "payload: " + decode.getPayload()
        + " " + "username: " + decode.getClaim("username") + " " + "role: " + decode.getClaim("role"));
        System.out.println("password: " + decode.getClaim("password").toString().replace("\"", ""));
    }
}
