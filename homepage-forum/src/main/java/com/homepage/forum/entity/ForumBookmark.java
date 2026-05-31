package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_bookmark")
public class ForumBookmark {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private Long postId;

    private Long folderId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
