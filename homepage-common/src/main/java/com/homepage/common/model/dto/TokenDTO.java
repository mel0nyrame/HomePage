package com.homepage.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.dto
 * @Date 2026/6/8 14:42
 * @description: 用于传输 refresh_token 和 access_token
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    /**
     * accessToken，时长 30 分钟
     */
    private String accessToken;

    /**
     * refreshToken，时长 7 天
     */
    private String refreshToken;
}
