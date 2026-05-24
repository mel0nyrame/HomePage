package com.homepage.auth.model.entity;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.model.entity
 * @Date 5/24/26 15:07
 * @description: 用户表，对应homepage_user
 */
@Setter
public class UserEntity implements UserDetails {

    /**
     * 用户id
     */
    public Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否启用，1为启用，0为关闭
     */
    public Integer enabled;

    /**
     * 用户权限，默认是USER
     */
    public String authorities;

    /**
     * 获取用户权限
     * @return 用户权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.authorities);
    }

    /**
     * 获取用户密码
     * @return 用户密码
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 获取是否开启
     * @return 布尔值
     */
    @Override
    public boolean isEnabled() {
        return this.enabled != null && this.enabled == 1;
    }

    /**
     * 用户是否过期
     * @return 布尔值
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否被锁定
     * @return 布尔值
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 获取用户凭证是否过期
     * @return 布尔值
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
