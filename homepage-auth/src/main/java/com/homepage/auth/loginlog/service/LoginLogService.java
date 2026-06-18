package com.homepage.auth.loginlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homepage.common.model.entity.LoginLogEntity;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.loginlog.service
 * @Date 6/15/26 18:00
 * @description: 登录日志服务
 */
public interface LoginLogService extends IService<LoginLogEntity> {

    /**
     * 记录日志
     *
     * @param request 请求
     * @param id      用户主键id
     */
    void recordLog(HttpServletRequest request, Long id);
}
