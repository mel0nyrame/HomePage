package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_follow")
public class ForumFollow {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String followerUsername;

    private String followeeUsername;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
