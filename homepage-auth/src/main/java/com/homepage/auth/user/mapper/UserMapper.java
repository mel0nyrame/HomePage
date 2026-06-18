package com.homepage.auth.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.common.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.user.mapper
 * @Date 5/24/26 15:15
 * @description: homepage_user表crud接口
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("SELECT id FROM homepage_user WHERE username = #{account} OR email = #{account}")
    Long selectIdByUsername(@Param("account") String account);
}
