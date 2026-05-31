package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_like_record")
public class ForumLikeRecord {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private Integer targetType;

    private Long targetId;

    private Integer type;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
