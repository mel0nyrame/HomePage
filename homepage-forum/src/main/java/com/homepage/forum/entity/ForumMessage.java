package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum_message")
public class ForumMessage {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String senderUsername;

    private String receiverUsername;

    private String content;

    private Integer isRead;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
