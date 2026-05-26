package com.homepage.auth.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.common.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.user.mapper
 * @Date 5/24/26 15:15
 * @description: homepage_user表crud接口
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
