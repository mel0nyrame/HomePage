package com.homepage.common.exception;

import com.homepage.common.web.ResponseCode;
import lombok.Data;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.exception
 * @Date 5/21/26 21:18
 * @description: 业务错误类
 */
@Data
public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(ResponseCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public BusinessException(ResponseCode resultCode, String message) {
        super(message);
        this.code = resultCode.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
