package com.homepage.auth.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.model.entity
 * @Date 5/25/26 21:31
 * @description: 管理员表实体，对应homepage_admin
 */
@Getter
@Setter
public class AdminEntity implements UserDetails {

    /**
     * 管理员id
     */
    private Long id;

    /**
     * 管理员账号
     */
    private String account;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 是否启用，1为启用，0为关闭
     */
    private Integer enabled;

    /**
     * 管理员权限，默认是ADMIN
     */
    private String authorities;

    /**
     * 获取管理员权限
     * @return 管理员权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.authorities);
    }

    /**
     * 获取管理员密码
     * @return 管理员密码
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 获取管理员名
     * @return 管理员名
     */
    @Override
    public String getUsername() {
        return this.account;
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
     * 管理员是否过期
     * @return 布尔值
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 管理员是否被锁定
     * @return 布尔值
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 获取管理员凭证是否过期
     * @return 布尔值
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}