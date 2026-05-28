package com.homepage.common.util;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.homepage.common.constant.RedisConstants.REDIS_EMAIL_CAPTCHA_PREFIX;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.util
 * @Date 5/28/26 21:38
 * @description: 邮箱工具类
 */
@Component
public class MailUtil {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;
    private final TemplateEngine templateEngine;

    public MailUtil(JavaMailSender mailSender, StringRedisTemplate redisTemplate, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.redisTemplate = redisTemplate;
        this.templateEngine = templateEngine;
    }

    /**
     * 给邮箱发送邮件
     *
     * @param email 邮箱地址
     */
    public void sendEmail(String email) {
        // 设置信息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom('<' + sender + '>');
        message.setTo(email);
        message.setSubject("欢迎访问Homepage");

        // 设置验证码并放入redis，设置过期时间为60秒
        String captcha = RandomUtil.randomNumbers(6);
        redisTemplate.opsForValue().set(REDIS_EMAIL_CAPTCHA_PREFIX + email,captcha,60, TimeUnit.SECONDS);

        // 设置邮箱内容并放入信息中
        String context = generateEmailContent(captcha);
        message.setText(context);

        // 发送信息
        mailSender.send(message);
    }

    /**
     * 生成邮件内容
     * @param captcha 验证码
     * @return {@link String } 邮件内容
     */
    private String generateEmailContent(String captcha) {
        Context context = new Context();
        context.setVariable("verifyCode", Arrays.asList(captcha.split("")));
        return templateEngine.process("EmailVerificationCode.html", context);
    }
}
