package com.homepage.common.exception;

import com.homepage.common.web.Response;
import com.homepage.common.web.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Mel0ny
 * @Package com.homepage.common
 * @Date 5/21/26 21:20
 * @description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<Void> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn("业务异常 [{}] {} -> {}", e.getCode(), request.getRequestURI(), e.getMessage());
        return Response.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常 [{}] {}", request.getRequestURI(), e.getMessage(), e);
        return Response.fail(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
