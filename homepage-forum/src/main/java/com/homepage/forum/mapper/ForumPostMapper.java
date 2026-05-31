package com.homepage.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.forum.entity.ForumPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForumPostMapper extends BaseMapper<ForumPost> {
}
