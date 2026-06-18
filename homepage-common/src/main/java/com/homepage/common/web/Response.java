package com.homepage.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "统一响应对象")
public class Response<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "状态码", example = "200")
    private final int code;
    @Schema(description = "提示信息", example = "ok")
    private final String message;
    @Schema(description = "响应数据")
    private final T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

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

    public static <T> Response<T> fail(ResponseCode responseCode) {
        return new Response<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> Response<T> fail(ResponseCode responseCode, String message) {
        return new Response<>(responseCode.getCode(), message, null);
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }
}
