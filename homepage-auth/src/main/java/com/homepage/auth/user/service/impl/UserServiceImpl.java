package com.homepage.auth.user.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homepage.auth.user.mapper.UserMapper;
import com.homepage.auth.user.service.UserService;
import com.homepage.common.exception.BusinessException;
import com.homepage.common.model.dto.EmailDTO;
import com.homepage.common.model.dto.LoginDTO;
import com.homepage.common.model.dto.RegisterDTO;
import com.homepage.common.model.dto.TokenDTO;
import com.homepage.common.model.entity.UserEntity;
import com.homepage.common.util.JwtUtil;
import com.homepage.common.util.MailUtil;
import com.homepage.common.util.RedisUtil;
import com.homepage.common.web.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.homepage.common.constant.RedisConstants.*;

/**
 * @Author Mel0ny
 * @Package com.homepage.auth.user.service.impl
 * @Date 5/23/26 01:48
 * @description: 用户业务实现类
 */
@Slf4j
@Primary
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RedisUtil redisUtil;
    private final StringRedisTemplate redisTemplate;
    private final MailUtil mailUtil;
    private final CompromisedPasswordChecker compromisedPasswordChecker;

    public UserServiceImpl(JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           @Qualifier("userAuthenticationManager") AuthenticationManager authenticationManager,
                           RedisUtil redisUtil,
                           StringRedisTemplate redisTemplate,
                           MailUtil mailUtil,
                           CompromisedPasswordChecker compromisedPasswordChecker
    ) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.redisUtil = redisUtil;
        this.redisTemplate = redisTemplate;
        this.mailUtil = mailUtil;
        this.compromisedPasswordChecker = compromisedPasswordChecker;
    }

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        // 验证验证码
        redisUtil.verifyCaptcha(loginDTO);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getAccount(), loginDTO.getPassword())
            );
            // refreshToken和accessToken
            String refreshToken = jwtUtil.generateRefreshToken(authentication);
            String accessToken = jwtUtil.generateAccessToken(authentication);

            // 向redis中添加token并且设置过期时间
            redisTemplate.opsForValue().set(REDIS_TOKEN_REFRESH_PREFIX + loginDTO.getAccount(),
                    refreshToken, 15, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(REDIS_TOKEN_ACCESS_PREFIX + loginDTO.getAccount(),
                    accessToken, 7, TimeUnit.DAYS);

            // 返回双token
            return new TokenDTO(accessToken
                    , refreshToken);
        } catch (BadCredentialsException e) {
            throw new BusinessException(ResponseCode.USER_PASSWORD_ERROR);
        } catch (DisabledException e) {
            throw new BusinessException(ResponseCode.USER_ACCOUNT_DISABLED);
        }
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        // 验证验证码
        redisUtil.verifyCaptcha(registerDTO);

        // 查询密码是否泄漏
        CompromisedPasswordDecision passwordDecision = compromisedPasswordChecker.check(registerDTO.getPassword());
        if (passwordDecision.isCompromised()) {
            throw new BusinessException(ResponseCode.USER_PASSWORD_LEAKED);
        }

        // 查询用户
        if (lambdaQuery()
                .eq(UserEntity::getUsername, registerDTO.getUsername())
                .or()
                .eq(UserEntity::getEmail, registerDTO.getEmail())
                .exists()) {
            if (lambdaQuery().eq(UserEntity::getUsername, registerDTO.getUsername()).exists()) {
                throw new BusinessException(ResponseCode.USER_ALREADY_EXIST);
            }
            throw new BusinessException(ResponseCode.USER_EMAIL_EXIST);
        }

        // 设置实体类
        UserEntity user = new UserEntity();
        user.setNickname(registerDTO.getNickname());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEnabled(1);
        user.setAuthorities("USER");

        // 将实体类转为json，放入redis，过期时间设为五分钟
        String userJson = JSONUtil.toJsonStr(user);
        redisTemplate.opsForValue().set(REDIS_USER_PREFIX + registerDTO.getEmail(), userJson, 5, TimeUnit.MINUTES);

        // 给输入的邮箱发送验证码邮件
        mailUtil.sendEmail(registerDTO.getEmail())
                .exceptionally(ex -> {
                    log.error("验证码邮件发送失败: {}", registerDTO.getEmail(), ex);
                    throw new BusinessException(ResponseCode.MESSAGE_SEND_FAILED);
                });
    }

    @Override
    public void verifyEmail(EmailDTO emailDTO) {
        // 验证邮箱验证码
        redisUtil.verifyEmailCaptcha(emailDTO.getEmail(), emailDTO.getCaptcha());

        // 获取存在redis中的用户信息，并删除验证码
        String userJson = redisTemplate.opsForValue().getAndDelete(REDIS_USER_PREFIX + emailDTO.getEmail());
        if (userJson == null) {
            throw new BusinessException(ResponseCode.USER_VERIFY_CODE_EXPIRED);
        }

        UserEntity user = JSONUtil.toBean(userJson, UserEntity.class);

        // 插入信息
        this.save(user);
    }

    @Override
    public String refreshToken(Authentication authentication) {

        return jwtUtil.generateRefreshToken(authentication);
    }

    @Override
    public void retryEmail(String email) {
        mailUtil.sendEmail(email)
                .exceptionally(ex -> {
                    log.error("重新发送验证码邮件失败: {}", email, ex);
                    throw new BusinessException(ResponseCode.MESSAGE_SEND_FAILED);
                });
    }
}
