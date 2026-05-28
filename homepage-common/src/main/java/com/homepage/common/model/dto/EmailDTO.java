package com.homepage.common.model.dto;

import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.model.dto
 * @Date 5/28/26 22:19
 * @description: 用于传输邮箱信息
 */
@Data
public class EmailDTO {
    private String email;
    private String captcha;
}
