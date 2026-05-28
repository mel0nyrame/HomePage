package com.homepage.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.homepage")
public class HomepageForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomepageForumApplication.class, args);
    }
}
