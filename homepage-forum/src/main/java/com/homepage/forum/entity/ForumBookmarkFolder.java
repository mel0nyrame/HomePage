package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_bookmark_folder")
public class ForumBookmarkFolder {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private String name;

    private Integer isPublic;

    private Integer sortOrder;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
