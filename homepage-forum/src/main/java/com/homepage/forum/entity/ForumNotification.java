package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_notification")
public class ForumNotification {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private Integer type;

    private String title;

    private String content;

    private Integer isRead;

    private Long relatedId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
