package com.homepage.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.entity
 * @Date 5/24/26 15:07
 * @description: 用户表，对应homepage_user
 */
@Data
@TableName("homepage_user")
public class UserEntity {

    /**
     * 用户id，使用雪花算法生成
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否启用，1为启用，0为关闭
     */
    private Integer enabled;

    /**
     * 用户权限，默认是USER
     */
    private String authorities;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
