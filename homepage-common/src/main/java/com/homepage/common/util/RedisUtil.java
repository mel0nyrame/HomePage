package com.homepage.common.util;

import com.homepage.common.exception.BusinessException;
import com.homepage.common.model.dto.CaptchaAware;
import com.homepage.common.web.ResponseCode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.homepage.common.constant.RedisConstants.REDIS_AUTH_CAPTCHA_PREFIX;
import static com.homepage.common.constant.RedisConstants.REDIS_EMAIL_CAPTCHA_PREFIX;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/28/26 10:32
 * @description: redis工具类
 */
@Component
public class RedisUtil {

    private static final String CAPTCHA_VERIFY_LUA = """
            local v = redis.call('GET', KEYS[1])
            if not v then return {'NOT_FOUND'} end
            if v == ARGV[1] then redis.call('DEL', KEYS[1]); return {'OK'} end
            return {'WRONG'}
            """;

    private static final String RESULT_OK = "OK";
    private static final String RESULT_WRONG = "WRONG";

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
        verifyAndDeleteCaptcha(key, inputCaptcha);
    }

    /**
     * 验证邮箱验证码（Lua 脚本原子化读取+删除，防止并发重放）
     *
     * @param email        邮箱地址
     * @param inputCaptcha 输入的验证码
     */
    public void verifyEmailCaptcha(String email, String inputCaptcha) {
        String key = REDIS_EMAIL_CAPTCHA_PREFIX + email;
        verifyAndDeleteCaptcha(key, inputCaptcha);
    }

    /**
     * 通用验证码验证逻辑（Lua 脚本原子化读取+删除）
     *
     * @param key          Redis键
     * @param inputCaptcha 输入的验证码
     */
    @SuppressWarnings("ConstantConditions")
    private void verifyAndDeleteCaptcha(String key, String inputCaptcha) {
        List<String> result = redisTemplate.execute(CAPTCHA_SCRIPT, List.of(key), inputCaptcha);
        if (result == null || result.isEmpty()) {
            throw new BusinessException(ResponseCode.USER_VERIFY_CODE_EXPIRED);
        }
        String status = result.getFirst();
        switch (status) {
            case RESULT_OK:
                return;
            case RESULT_WRONG:
                throw new BusinessException(ResponseCode.USER_VERIFY_CODE_ERROR);
            default:
                throw new BusinessException(ResponseCode.USER_VERIFY_CODE_EXPIRED);
        }
    }
}