package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_post")
public class ForumPost {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String authorUsername;

    private Long boardId;

    private String title;

    private String content;

    private Integer status;

    private Integer isPinned;

    private Integer isEssence;

    private LocalDateTime editTime;

    private Integer viewCount;

    private Integer likeCount;

    private Integer dislikeCount;

    private Integer bookmarkCount;

    private Integer commentCount;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
