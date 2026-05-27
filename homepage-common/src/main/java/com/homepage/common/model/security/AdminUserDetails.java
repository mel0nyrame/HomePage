package com.homepage.common.model.security;

import com.homepage.common.model.entity.AdminEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.security
 * @Date 5/27/26
 * @description: Spring Security 管理员认证主体，包装 AdminEntity
 */
public class AdminUserDetails implements UserDetails {

    private final AdminEntity admin;

    public AdminUserDetails(AdminEntity admin) {
        this.admin = admin;
    }

    /**
     * 获取管理员权限
     * @return 管理员权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(admin.getAuthorities());
    }

    /**
     * 获取管理员密码
     * @return 管理员密码
     */
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    /**
     * 获取管理员名
     * @return 管理员名
     */
    @Override
    public String getUsername() {
        return admin.getAccount();
    }

    /**
     * 获取是否开启
     * @return 布尔值
     */
    @Override
    public boolean isEnabled() {
        return admin.getEnabled() != null && admin.getEnabled() == 1;
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
