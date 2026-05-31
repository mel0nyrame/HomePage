package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_comment")
public class ForumComment {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long postId;

    private String authorUsername;

    private Long parentId;

    private Long rootId;

    private String content;

    private Integer status;

    private Integer likeCount;

    private Integer dislikeCount;

    private Integer replyCount;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
