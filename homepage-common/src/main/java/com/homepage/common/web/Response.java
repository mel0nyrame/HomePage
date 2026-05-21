package com.homepage.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.web
 * @Date 5/21/26 21:21
 * @description: 响应类
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private final int code;
    private final String message;
    private final T data;

    public static <T> Response<T> ok() {
        return new Response<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> Response<T> ok(String message, T data) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Response<T> fail() {
        return new Response<>(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    public static <T> Response<T> fail(ResponseCode ResponseCode) {
        return new Response<>(ResponseCode.getCode(), ResponseCode.getMessage(), null);
    }

    public static <T> Response<T> fail(ResponseCode ResponseCode, String message) {
        return new Response<>(ResponseCode.getCode(), message, null);
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }
}
