package com.homepage.common.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 6/15/26 17:26
 * @description: 获取ip信息工具类
 */
@Component
public class GeoIpUtil {

    private final DatabaseReader reader;

    public GeoIpUtil(DatabaseReader reader) {
        this.reader = reader;
    }

    public CityResponse getCity(String ip) throws Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        return reader.city(ipAddress);
    }

}
