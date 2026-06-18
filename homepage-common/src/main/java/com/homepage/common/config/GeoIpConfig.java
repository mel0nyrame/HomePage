package com.homepage.common.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.config
 * @Date 6/15/26 17:24
 * @description: 配置ip地址数据库
 */
@Configuration
public class GeoIpConfig {

    @Bean(destroyMethod = "close")
    public DatabaseReader databaseReader() throws Exception {
        try (InputStream database = new ClassPathResource("geoip/GeoLite2-City-CN.mmdb").getInputStream()) {
            return new DatabaseReader.Builder(database).build();
        }
    }
}
