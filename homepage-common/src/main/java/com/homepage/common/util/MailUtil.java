package com.homepage.common.util;

import cn.hutool.core.util.RandomUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    private static final int CAPTCHA_LENGTH = 6;
    private static final long CAPTCHA_EXPIRE_SECONDS = 5 * 60;
    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String sender;

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
    @Async
    public CompletableFuture<Void> sendEmail(String email) {
        try {
            // 设置信息
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject("HomePage 邮箱验证码");

            // 设置验证码并放入redis
            String captcha = RandomUtil.randomNumbers(CAPTCHA_LENGTH);
            redisTemplate.opsForValue().set(REDIS_EMAIL_CAPTCHA_PREFIX + email, captcha, CAPTCHA_EXPIRE_SECONDS, TimeUnit.SECONDS);

            // 设置邮箱内容并放入信息中
            String context = generateEmailContent(captcha);
            helper.setText(context, true);

            // 发送信息
            mailSender.send(message);

            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    /**
     * 生成邮件内容
     *
     * @param captcha 验证码
     * @return {@link String } 邮件内容
     */
    private String generateEmailContent(String captcha) {
        Context context = new Context();
        // 将验证码拆分为单个字符列表，便于模板中逐个显示
        List<String> captchaChars = captcha.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toList();
        context.setVariable("verifyCode", captchaChars);
        return templateEngine.process("EmailVerificationCode.html", context);
    }
}
