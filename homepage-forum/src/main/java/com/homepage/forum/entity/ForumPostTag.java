package com.homepage.forum.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("forum_post_tag")
public class ForumPostTag {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long postId;

    private Long tagId;
}
