package com.homepage.auth.loginlog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homepage.auth.loginlog.mapper.LoginLogMapper;
import com.homepage.auth.loginlog.service.LoginLogService;
import com.homepage.common.model.entity.LoginLogEntity;
import com.homepage.common.util.GeoIpUtil;
import com.maxmind.geoip2.NamedRecord;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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
     * 把 IP 归一为 UNKNOWN：空白与 Hutool 在无代理头时返回的小写 "unknown" 都视为未知，
     * 避免污染 DB 列与绕过 {@link #resolveLocation} 的短路守卫。
     */
    private static String normalizeIp(String ip) {
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            return UNKNOWN;
        }
        return ip;
    }

    private static String extractOs(String ua) {
        if (StrUtil.isEmpty(ua)) {
            return UNKNOWN;
        }
        try {
            return UserAgentUtil.parse(ua).getOs().getName();
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    /**
     * 取 MaxMind 命名记录的 name 字段。City 与 Country 都实现 {@link NamedRecord}，统一处理。
     */
    private static String nameOf(NamedRecord record) {
        if (record == null) {
            return "";
        }
        return StrUtil.nullToEmpty(record.name());
    }

    /**
     * 记录登录日志（异步执行，与登录主流程解耦）。
     */
    @Override
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordLog(HttpServletRequest request, Long id) {
        if (request == null) {
            return;
        }
        try {
            String ip = normalizeIp(JakartaServletUtil.getClientIP(request));
            String ua = StrUtil.blankToDefault(request.getHeader("User-Agent"), "");

            LoginLogEntity entity = new LoginLogEntity();
            entity.setUserId(id);
            entity.setLoginIp(ip);
            entity.setUserAgent(ua);
            entity.setDeviceOs(extractOs(ua));
            entity.setLocation(resolveLocation(ip));
            this.save(entity);
        } catch (Exception e) {
            log.error("记录登录日志失败, userId={}", id, e);
        }
    }

    private String resolveLocation(String ip) {
        if (UNKNOWN.equals(ip)) {
            return UNKNOWN;
        }
        try {
            CityResponse response = geoIpUtil.getCity(ip);
            String joined = (nameOf(response.country()) + " " + nameOf(response.city())).trim();
            if (StrUtil.isBlank(joined)) {
                return UNKNOWN;
            }
            return joined;
        } catch (Exception e) {
            return UNKNOWN;
        }
    }
}