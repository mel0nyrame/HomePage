package com.homepage.auth.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homepage.common.model.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.admin.mapper
 * @Date 5/25/26 21:21
 * @description: homepage_admin表crud接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {
}
