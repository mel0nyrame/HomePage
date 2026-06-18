package com.homepage.common.security;

import com.homepage.common.util.JwtUtil;
import com.homepage.common.util.TokenStore;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.security
 * @Date 2026/6/10
 * @description: 自定义 JWT 校验过滤器。
 * 加在 {@link BearerTokenAuthenticationFilter} 之后，
 * 在 Spring Security 默认验签 + 验 exp 的基础上，额外校验：
 * 1. type claim 必须是 access_token（防止 refresh_token 被当 access_token 用）
 * 2. access token jti 在 Redis 中仍存在（防吊销 / 防过期）
 * 校验失败：清空 SecurityContext + 调 AuthenticationEntryPoint 返回 401。
 * <p>
 * 不替代 Spring Security 默认的 BearerToken 解析流程，仅作为后置增强。
 */
@Component
public class JwtValidationFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;
    private final AuthenticationEntryPoint entryPoint = new BearerTokenAuthenticationEntryPoint();

    public JwtValidationFilter(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 只对已经通过 BearerTokenAuthenticationFilter 解析出的 Jwt 主体做后置校验
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            try {
                String type = jwt.getClaimAsString("type");
                if (!JwtUtil.TYPE_ACCESS.equals(type)) {
                    reject(request, response, "token 类型错误，期望 access_token");
                    return;
                }

                String jti = jwt.getId();
                if (!tokenStore.isAccessValid(jti, jwt.getTokenValue())) {
                    reject(request, response, "token 已失效或被吊销");
                    return;
                }
            } catch (AuthenticationException e) {
                reject(request, response, e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void reject(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        // 复用 Spring Security 的 BearerToken 错误响应，body 由 GlobalExceptionHandler / EntryPoint 决定
        entryPoint.commence(request, response, new org.springframework.security.oauth2.server.resource.InvalidBearerTokenException(message));
    }
}
