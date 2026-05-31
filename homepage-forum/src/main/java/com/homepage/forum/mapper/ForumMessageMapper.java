package com.homepage.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.forum.entity.ForumMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForumMessageMapper extends BaseMapper<ForumMessage> {
}
