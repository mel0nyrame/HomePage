package com.homepage.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth
 * @Date 5/23/26 01:54
 * @description: 认证服务启动类
 */
@SpringBootApplication
@ComponentScan("com.homepage")
@MapperScan({"com.homepage.auth.user.mapper", "com.homepage.auth.admin.mapper", "com.homepage.auth.loginlog.mapper"})
@EnableAsync
public class HomepageAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomepageAuthApplication.class, args);
    }
}
