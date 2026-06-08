package com.homepage.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.dto
 * @Date 2026/6/8 14:42
 * @description: 用于传输refresh_token和access_token
 */
@Data
@AllArgsConstructor
public class TokenDTO {

    /**
     * refreshToken，时长15分钟
     */
    private String refreshToken;

    /**
     * accessToken，时长7天
     */
    private String accessToken;
}
