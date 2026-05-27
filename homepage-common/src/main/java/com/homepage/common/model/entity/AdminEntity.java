package com.homepage.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.entity
 * @Date 5/25/26 21:31
 * @description: 管理员表实体，对应homepage_admin
 */
@Data
@TableName("homepage_admin")
public class AdminEntity {

    /**
     * 管理员id，使用雪花算法生成
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 管理员昵称
     */
    private String nickname;

    /**
     * 管理员账号
     */
    private String account;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 是否启用，1为启用，0为关闭
     */
    private Integer enabled;

    /**
     * 管理员权限，默认是ADMIN
     */
    @TableField("authorities")
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
