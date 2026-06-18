package com.homepage.auth.loginlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.common.model.entity.LoginLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.loginlog.mapper
 * @Date 6/15/26 18:00
 * @description: 登录日志 Mapper
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogEntity> {
}
