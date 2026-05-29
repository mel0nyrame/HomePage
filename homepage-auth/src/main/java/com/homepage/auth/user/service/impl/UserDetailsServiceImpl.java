package com.homepage.auth.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.homepage.auth.user.mapper.UserMapper;
import com.homepage.common.model.entity.UserEntity;
import com.homepage.common.model.security.HomepageUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.user.service.impl
 * @Date 5/29/26 01:10
 * @description: 独立的用户认证服务
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserEntity user = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>().and(
                        w -> w.eq(UserEntity::getUsername, account)
                                .or()
                                .eq(UserEntity::getEmail, account)
                )
        );
        if (user == null) {
            throw new UsernameNotFoundException(account);
        }
        return new HomepageUserDetails(user);
    }
}
