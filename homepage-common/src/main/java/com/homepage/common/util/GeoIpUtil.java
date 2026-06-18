package com.homepage.common.util;

import cn.hutool.core.lang.Validator;
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

    /**
     * 解析 IP 归属地。仅接受 IP 字面量（IPv4/IPv6），
     * 拒绝主机名以避免对攻击者可控输入（如 X-Forwarded-For）触发 DNS 解析。
     *
     * @param ip IP 字面量字符串
     * @return MaxMind CityResponse
     * @throws IllegalArgumentException 输入不是 IP 字面量
     * @throws Exception                MaxMind 查询失败
     */
    public CityResponse getCity(String ip) throws Exception {
        if (!Validator.isIpv4(ip) && !Validator.isIpv6(ip)) {
            throw new IllegalArgumentException("拒绝非 IP 字面量输入: " + ip);
        }
        InetAddress ipAddress = InetAddress.getByName(ip);
        return reader.city(ipAddress);
    }
}