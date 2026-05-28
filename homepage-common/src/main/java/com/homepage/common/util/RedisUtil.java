package com.homepage.common.util;

import com.homepage.common.exception.BusinessException;
import com.homepage.common.model.dto.CaptchaAware;
import com.homepage.common.web.ResponseCode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.homepage.common.constant.RedisConstants.REDIS_AUTH_CAPTCHA_PREFIX;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/28/26 10:32
 * @description: redis工具类
 */
@Component
public class RedisUtil {

    private static final String CAPTCHA_VERIFY_LUA =
            "local v = redis.call('GET', KEYS[1]) " +
            "if v then redis.call('DEL', KEYS[1]) end " +
            "return {v}";

    @SuppressWarnings("unchecked")
    private static final RedisScript<List<String>> CAPTCHA_SCRIPT =
            (RedisScript<List<String>>) (RedisScript<?>) RedisScript.of(CAPTCHA_VERIFY_LUA, List.class);

    private final StringRedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 验证验证码
     *
     * @param dto 实现了 CaptchaAware 的 DTO
     */
    public void verifyCaptcha(CaptchaAware dto) {
        verifyCaptcha(dto.getCaptchaId(), dto.getCaptcha());
    }

    /**
     * 验证验证码（Lua 脚本原子化读取+删除，防止并发重放）
     *
     * @param captchaId    验证码id
     * @param inputCaptcha 输入的验证码
     */
    public void verifyCaptcha(String captchaId, String inputCaptcha) {
        String key = REDIS_AUTH_CAPTCHA_PREFIX + captchaId;
        List<String> result = redisTemplate.execute(CAPTCHA_SCRIPT, List.of(key));
        String captcha = !result.isEmpty() ? result.getFirst() : null;
        if (captcha == null) {
            throw new BusinessException(ResponseCode.USER_VERIFY_CODE_EXPIRED);
        }
        if (!captcha.equals(inputCaptcha)) {
            throw new BusinessException(ResponseCode.USER_VERIFY_CODE_ERROR);
        }
    }
}