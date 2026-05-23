package com.homepage.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Mel0ny
 * @Package com.homepage.user
 * @Date 5/23/26 01:54
 * @description:
 */
@SpringBootApplication
@ComponentScan("com.homepage")
public class HomepageAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomepageAuthApplication.class, args);
    }
}
