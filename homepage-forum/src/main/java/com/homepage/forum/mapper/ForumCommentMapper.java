package com.homepage.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.forum.entity.ForumComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForumCommentMapper extends BaseMapper<ForumComment> {
}
