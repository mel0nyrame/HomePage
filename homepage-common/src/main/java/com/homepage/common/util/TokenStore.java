package com.homepage.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.homepage.common.constant.RedisConstants.*;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 2026/6/10
 * @description: Token 在 Redis 中的存取与吊销工具。
 * key 设计：
 * token:access:{jti}        -> accessToken 字符串
 * token:refresh:{jti}       -> refreshToken 字符串
 * token:user:refresh:{user} -> Set<jti> 该用户所有 refresh 会话
 * token:refresh:index       -> Hash jti -> username
 */
@Component
public class TokenStore {

    private final StringRedisTemplate redisTemplate;

    public TokenStore(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 存储 accessToken
     *
     * @param jti        access token 的 jti
     * @param token      完整 token 字符串
     * @param ttlSeconds 过期秒数
     */
    public void saveAccess(String jti, String token, long ttlSeconds) {
        redisTemplate.opsForValue().set(REDIS_TOKEN_ACCESS_PREFIX + jti, token, ttlSeconds, TimeUnit.SECONDS);
    }

    /**
     * 存储 refreshToken，同时把 (jti -> username) 写入索引，并把 jti 加入用户会话集合
     *
     * @param jti        refresh token 的 jti
     * @param username   所属用户名
     * @param token      完整 token 字符串
     * @param ttlSeconds 过期秒数
     */
    public void saveRefresh(String jti, String username, String token, long ttlSeconds) {
        redisTemplate.opsForValue().set(REDIS_TOKEN_REFRESH_PREFIX + jti, token, ttlSeconds, TimeUnit.SECONDS);
        redisTemplate.opsForHash().put(REDIS_REFRESH_INDEX, jti, username);
        redisTemplate.opsForSet().add(REDIS_USER_REFRESH_SET_PREFIX + username, jti);
        // 用户会话集合的过期时间略长于 refresh，保证即便 refresh 过期也不会出现"找不到 jti 反查用户"的边角
        redisTemplate.expire(REDIS_USER_REFRESH_SET_PREFIX + username, ttlSeconds + 60, TimeUnit.SECONDS);
    }

    /**
     * 校验 accessToken 是否有效：Redis 中存在且与传入 token 一致
     */
    public boolean isAccessValid(String jti, String token) {
        if (!StrUtil.isAllNotBlank(jti, token)) {
            return false;
        }
        String stored = redisTemplate.opsForValue().get(REDIS_TOKEN_ACCESS_PREFIX + jti);
        return token.equals(stored);
    }

    /**
     * 校验 refreshToken 是否有效
     */
    public boolean isRefreshValid(String jti, String token) {
        if (!StrUtil.isAllNotBlank(jti, token)) {
            return false;
        }
        String stored = redisTemplate.opsForValue().get(REDIS_TOKEN_REFRESH_PREFIX + jti);
        return token.equals(stored);
    }

    /**
     * 根据 refresh jti 反查 username
     */
    public String getUsernameByRefreshJti(String jti) {
        Object username = redisTemplate.opsForHash().get(REDIS_REFRESH_INDEX, jti);
        return username == null ? null : username.toString();
    }

    /**
     * 吊销指定 jti 的 access + refresh
     */
    public void revoke(String jti) {
        redisTemplate.delete(REDIS_TOKEN_ACCESS_PREFIX + jti);
        redisTemplate.delete(REDIS_TOKEN_REFRESH_PREFIX + jti);
        redisTemplate.opsForHash().delete(REDIS_REFRESH_INDEX, jti);
    }

    /**
     * 吊销该用户所有 refresh 会话（全部下线）
     *
     * @return 被吊销的 jti 数量
     */
    public long revokeAllByUsername(String username) {
        String setKey = REDIS_USER_REFRESH_SET_PREFIX + username;
        Set<String> jtis = redisTemplate.opsForSet().members(setKey);
        if (CollUtil.isEmpty(jtis)) {
            redisTemplate.delete(setKey);
            return 0L;
        }
        jtis.forEach(this::revoke);
        redisTemplate.delete(setKey);
        return jtis.size();
    }
}
