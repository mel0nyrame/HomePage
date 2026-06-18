package com.homepage.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.entity
 * @Date 6/15/26 15:57
 * @description: 登陆记录表实体，对应homepage_login_log
 */
@Data
@TableName("homepage_login_log")
public class LoginLogEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户/管理员id
     */
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime loginTime;

    /**
     * 登陆ip
     */
    private String loginIp;

    /**
     * 登陆特征头
     */
    private String userAgent;

    /**
     * 设备系统
     */
    private String deviceOs;

    /**
     * 登陆地点
     */
    private String location;
}
