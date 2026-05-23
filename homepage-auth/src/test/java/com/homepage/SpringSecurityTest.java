package com.homepage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Mel0ny
 * @Package com.homepage
 * @Date 5/22/26 18:20
 * @description: SpringSecurity密码加密测试
 */
@SpringBootTest(classes = SpringSecurityTest.class)
public class SpringSecurityTest {

    @Test
    public void test() {
        PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String encode = pwEncoder.encode("password");
        System.out.println(encode);

        boolean matches = pwEncoder.matches("password", encode);
        System.out.println(matches);

        PasswordEncoder pwEncoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode1 = pwEncoder2.encode("password");
        System.out.println(encode1);
    }
}
