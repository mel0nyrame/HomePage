package com.homepage.auth.captcha.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.UUID;
import com.homepage.auth.captcha.service.CaptchaService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static com.homepage.common.constant.RedisConstants.REDIS_AUTH_CAPTCHA_PREFIX;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.captcha.service
 * @Date 5/28/26 01:12
 * @description: 验证码服务接口实现类
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private static final int CAPTCHA_WIDTH = 250;
    private static final int CAPTCHA_HEIGHT = 50;
    private static final int CAPTCHA_THICKNESS = 6;

    private final StringRedisTemplate redisTemplate;

    public CaptchaServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Captcha captcha() {
        // 随机数生成器
        RandomGenerator randomGenerator = new RandomGenerator(5);
        // 获取弯曲的验证码
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGHT,
                randomGenerator, CAPTCHA_THICKNESS);
        // 获取验证码
        String code = shearCaptcha.getCode();

        // captchaId
        String uuid = UUID.randomUUID().toString();
        // redis的key
        String key = REDIS_AUTH_CAPTCHA_PREFIX + uuid;
        // 验证码放入redis，过期时间为60秒
        redisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);

        String image;
        try {
            // 字节输出流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 将图片写入输出流
            ImageIO.write(shearCaptcha.getImage(), "png", bos);
            byte[] bytes = bos.toByteArray();
            // base64编码
            Base64.Encoder encoder = Base64.getEncoder();
            image = "data:image/png;base64," + encoder.encodeToString(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new CaptchaService.Captcha(uuid, image);
    }
}
