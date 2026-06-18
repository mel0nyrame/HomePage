package com.homepage.common.model.security;

import com.homepage.common.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.security
 * @Date 5/27/26
 * @description: Spring Security 用户认证主体，包装 UserEntity
 */
public class HomepageUserDetails implements UserDetails {

    private final UserEntity user;

    public HomepageUserDetails(UserEntity user) {
        this.user = user;
    }

    /**
     * 获取用户权限
     *
     * @return 用户权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
    }

    /**
     * 获取用户密码
     *
     * @return 用户密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 获取是否开启
     *
     * @return 布尔值
     */
    @Override
    public boolean isEnabled() {
        return user.getEnabled() != null && user.getEnabled() == 1;
    }

    /**
     * 用户是否过期
     *
     * @return 布尔值
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否被锁定
     *
     * @return 布尔值
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 获取用户凭证是否过期
     *
     * @return 布尔值
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 获取用户主键 id，供登录日志等场景直接复用，避免额外 SQL。
     *
     * @return 用户主键
     */
    public Long getId() {
        return user.getId();
    }
}
