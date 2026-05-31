package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_report")
public class ForumReport {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String reporterUsername;

    private Integer targetType;

    private Long targetId;

    private String reason;

    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
