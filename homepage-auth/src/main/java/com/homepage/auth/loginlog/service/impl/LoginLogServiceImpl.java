package com.homepage.auth.loginlog.service.impl;

import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homepage.auth.loginlog.mapper.LoginLogMapper;
import com.homepage.auth.loginlog.service.LoginLogService;
import com.homepage.common.model.entity.LoginLogEntity;
import com.homepage.common.util.GeoIpUtil;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.loginlog.service.impl
 * @Date 6/15/26 18:00
 * @description: 登录日志服务实现
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogEntity> implements LoginLogService {

    private static final String UNKNOWN = "Unknown";

    private final GeoIpUtil geoIpUtil;

    public LoginLogServiceImpl(GeoIpUtil geoIpUtil) {
        this.geoIpUtil = geoIpUtil;
    }

    /**
     * 记录登录日志。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordLog(HttpServletRequest request,Long id) {
        if (request == null) {
            return;
        }
        try {
            String ip = JakartaServletUtil.getClientIP(request);
            String ua = request.getHeader("User-Agent");

            LoginLogEntity entity = new LoginLogEntity();
            entity.setUserId(id);
            entity.setLoginIp(ip == null || ip.isBlank() ? UNKNOWN : ip);
            entity.setUserAgent(ua == null ? "" : ua);
            entity.setDeviceOs(resolveDeviceOs(ua));
            entity.setLocation(resolveLocation(entity.getLoginIp()));
            this.save(entity);
        } catch (Exception e) {
            log.error("记录登录日志失败, userId={}", id, e);
        }
    }

    private String resolveDeviceOs(String ua) {
        if (ua == null || ua.isBlank()) {
            return UNKNOWN;
        }
        try {
            return UserAgentUtil.parse(ua).getOs().toString();
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    private String resolveLocation(String ip) {
        if (ip == null || ip.isBlank() || UNKNOWN.equals(ip)) {
            return UNKNOWN;
        }
        try {
            CityResponse cityResponse = geoIpUtil.getCity(ip);
            String country = cityResponse.country().name();
            String city = cityResponse.city().name();
            return country + " " + city;
        } catch (Exception e) {
            return UNKNOWN;
        }
    }
}
