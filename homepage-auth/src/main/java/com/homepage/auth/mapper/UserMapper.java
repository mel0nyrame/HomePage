package com.homepage.auth.mapper;

import com.homepage.auth.model.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.mapper
 * @Date 5/24/26 15:15
 * @description: homepage_user表crud接口
 */
@Mapper
public interface UserMapper{

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Select("SELECT id,nickname,username,password,enabled,authorities " +
            "FROM homepage_user WHERE username = #{username}")
    UserEntity selectUser(String username);

    /**
     * 通过用户名查找用户数量，用于查看用户是否重复
     *
     * @param username 用户名
     * @return 用户数量
     */
    @Select("SELECT COUNT(*) FROM homepage_user WHERE username = #{username}")
    int existsByUsername(String username);

    /**
     * 插入用户
     *
     * @param user 用户
     * @return 成功数量
     */
    @Insert("INSERT INTO homepage_user (nickname, username, password) " +
            "VALUES (#{nickname}, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserEntity user);
}
