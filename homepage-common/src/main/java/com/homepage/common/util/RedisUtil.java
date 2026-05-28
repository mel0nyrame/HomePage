package com.homepage.common.util;

import com.homepage.common.constant.RedisConstants;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.web.ResponseCode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.homepage.common.constant.RedisConstants.REDIS_AUTH_CAPTCHA_PREFIX;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/28/26 10:32
 * @description: redis工具类
 */
@Component
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 验证验证码
     *
     * @param captchaId    验证码id
     * @param inputCaptcha 输入的验证码
     */
    public void verifyCaptcha(String captchaId, String inputCaptcha) {
        String captcha = getCaptcha(REDIS_AUTH_CAPTCHA_PREFIX + captchaId);
        if (captcha == null) {
            throw new BusinessException(ResponseCode.USER_VERIFY_CODE_EXPIRED);
        }
        if (!captcha.equalsIgnoreCase(inputCaptcha)) {
            throw new BusinessException(ResponseCode.USER_VERIFY_CODE_ERROR);
        }
        redisTemplate.delete(REDIS_AUTH_CAPTCHA_PREFIX + captchaId);
    }


    /**
     * 从redis中获取验证码
     *
     * @param key redis的key
     * @return redis中的验证码
     */
    public String getCaptcha(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
