package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_tag")
public class ForumTag {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer type;

    private Integer auditStatus;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
