package com.homepage.common.util;

import cn.hutool.core.util.StrUtil;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.web.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import static com.homepage.common.constant.JwtConstants.JWT_ACCESS_TOKEN_EXPIRATION_TIME;
import static com.homepage.common.constant.JwtConstants.JWT_REFRESH_TOKEN_EXPIRATION_TIME;
import static com.homepage.common.util.JwtUtil.TYPE_REFRESH;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 2026/6/10
 * @description: Token 业务层。统一负责签发、旋转、吊销、解析。
 * User 与 Admin 各自的 service 只需调本类即可。
 */
@Slf4j
@Component
public class TokenService {

    private final JwtUtil jwtUtil;
    private final JwtDecoder jwtDecoder;
    private final TokenStore tokenStore;

    public TokenService(JwtUtil jwtUtil, JwtDecoder jwtDecoder, TokenStore tokenStore) {
        this.jwtUtil = jwtUtil;
        this.jwtDecoder = jwtDecoder;
        this.tokenStore = tokenStore;
    }

    /**
     * 签发 (access, refresh) 对，写入 Redis，返回 TokenDTO
     */
    public TokenDTO issueTokenPair(Authentication authentication) {
        String accessToken = jwtUtil.generateAccessToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication);

        String accessJti = parseJti(accessToken);
        String refreshJti = parseJti(refreshToken);

        long accessTtl = JWT_ACCESS_TOKEN_EXPIRATION_TIME / 1000;
        long refreshTtl = JWT_REFRESH_TOKEN_EXPIRATION_TIME / 1000;

        if (StrUtil.isNotBlank(accessJti)) {
            tokenStore.saveAccess(accessJti, accessToken, accessTtl);
        }
        if (StrUtil.isNotBlank(refreshJti)) {
            tokenStore.saveRefresh(refreshJti, authentication.getName(), refreshToken, refreshTtl);
        }

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 用 refreshToken 换新 (access, refresh) 对，带 rotation 和 reuse-detection
     *
     * @param refreshToken       客户端传来的 refresh_token 字符串
     * @param userDetailsService 用来按 username 重新加载 Authentication 的服务
     */
    public TokenDTO rotate(String refreshToken, UserDetailsService userDetailsService) {
        Jwt jwt = decodeOrThrow(refreshToken);

        if (!TYPE_REFRESH.equals(jwt.getClaimAsString("type"))) {
            throw new BusinessException(ResponseCode.USER_TOKEN_INVALID);
        }

        String jti = jwt.getId();
        String username = jwt.getSubject();

        // 正常路径：旧 refresh 仍有效 → 吊销旧的、重新签发
        if (tokenStore.isRefreshValid(jti, refreshToken)) {
            tokenStore.revoke(jti);
            return issueTokenPair(loadAuthentication(username, userDetailsService));
        }

        // 异常路径：旧 refresh 已经失效但又出现 → 视为复用，踢掉该账号所有会话
        String indexedUsername = tokenStore.getUsernameByRefreshJti(jti);
        if (StrUtil.isNotBlank(indexedUsername)) {
            tokenStore.revokeAllByUsername(indexedUsername);
            log.warn("检测到 refresh token 复用，jti={}，已吊销账号 {} 所有会话", jti, indexedUsername);
        }
        throw new BusinessException(ResponseCode.USER_TOKEN_INVALID);
    }

    /**
     * 当前会话登出：吊销传入 jti 对应的 access + refresh
     */
    public void revoke(String accessJti) {
        if (StrUtil.isBlank(accessJti)) {
            return;
        }
        tokenStore.revoke(accessJti);
    }

    /**
     * 吊销指定账号所有会话
     */
    public long revokeAll(String username) {
        return tokenStore.revokeAllByUsername(username);
    }

    private Jwt decodeOrThrow(String token) {
        try {
            return jwtUtil.decode(token, jwtDecoder);
        } catch (JwtException e) {
            throw new BusinessException(ResponseCode.USER_TOKEN_INVALID);
        }
    }

    private String parseJti(String token) {
        try {
            return jwtUtil.decode(token, jwtDecoder).getId();
        } catch (JwtException e) {
            return null;
        }
    }

    private Authentication loadAuthentication(String username, UserDetailsService userDetailsService) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new BusinessException(ResponseCode.USER_NOT_EXIST);
        }
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
    }
}
