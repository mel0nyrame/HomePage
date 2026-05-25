package com.homepage.auth.mapper;

import com.homepage.auth.model.entity.AdminEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.mapper
 * @Date 5/25/26 21:21
 * @description: homepage_admin表crud接口
 */
@Mapper
public interface AdminMapper {

    @Select("SELECT id,account,password,enabled,authorities " +
            "FROM homepage_admin WHERE account = #{account}")
    AdminEntity selectByAccount(String account);

    /**
     * 通过管理员账号查找管理员数量，用于查看管理员是否重复
     *
     * @param account 管理员账号
     * @return 管理员数量
     */
    @Select("SELECT COUNT(*) FROM homepage_admin WHERE account = #{account}")
    int existsByUsername(String account);

    /**
     * 查看管理员数量
     * @return 管理员数量
     */
    @Select("SELECT COUNT(*) FROM homepage_admin")
    int existsByAccount();

    /**
     * 插入管理员
     * @param admin 管理员
     * @return 成功数量
     */
    @Insert("INSERT INTO homepage_admin(account, password) " +
            "VALUES (#{account},#{password})")
    int insertAdmin(AdminEntity admin);
}
