package com.homepage.user;

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
public class HomepageUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomepageUserApplication.class, args);
    }
}
