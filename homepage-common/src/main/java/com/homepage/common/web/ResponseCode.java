package com.homepage.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author Mel0ny
 * @Package com.homepage.common.web
 * @Date 5/21/26 21:25
 * @description: 响应状态码枚举类
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    /**
     * 成功响应
     */
    SUCCESS(200, "操作成功"),
    CREATED(201, "创建成功"),
    ACCEPTED(202, "请求已接受"),
    NO_CONTENT(204, "无内容"),

    /**
     * 客户端错误
     */
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权，请先登录"),
    FORBIDDEN(403, "拒绝访问，权限不足"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    CONFLICT(409, "资源冲突"),
    GONE(410, "资源已删除"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),
    PARAM_VALID_ERROR(460, "参数校验失败"),
    PARAM_MISSING(461, "缺少必要参数"),
    PARAM_TYPE_ERROR(462, "参数类型错误"),

    /**
     * 服务端错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务暂时不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),

    /**
     * 用户模块
     */
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_ALREADY_EXIST(1002, "用户已存在"),
    USER_PASSWORD_ERROR(1003, "密码错误"),
    USER_ACCOUNT_DISABLED(1004, "账号已被禁用"),
    USER_ACCOUNT_LOCKED(1005, "账号已被锁定"),
    USER_LOGIN_EXPIRED(1006, "登录已过期，请重新登录"),
    USER_TOKEN_INVALID(1007, "Token无效或已过期"),
    USER_OLD_PASSWORD_ERROR(1008, "原密码错误"),
    USER_EMAIL_EXIST(1009, "邮箱已被注册"),
    USER_PHONE_EXIST(1010, "手机号已被注册"),
    USER_EMAIL_NOT_BIND(1011, "邮箱未绑定"),
    USER_PHONE_NOT_BIND(1012, "手机号未绑定"),
    USER_VERIFY_CODE_ERROR(1013, "验证码错误"),
    USER_VERIFY_CODE_EXPIRED(1014, "验证码已过期"),
    USER_PASSWORD_LEAKED(1015, "密码已泄露，请更换密码"),

    /**
     * 文件模块
     */
    FILE_UPLOAD_ERROR(1101, "文件上传失败"),
    FILE_DOWNLOAD_ERROR(1102, "文件下载失败"),
    FILE_NOT_EXIST(1103, "文件不存在"),
    FILE_SIZE_EXCEED(1104, "文件大小超出限制"),
    FILE_TYPE_NOT_SUPPORT(1105, "不支持的文件类型"),
    FILE_READ_ERROR(1106, "文件读取失败"),
    FILE_WRITE_ERROR(1107, "文件写入失败"),

    /**
     * 数据模块
     */
    DATA_NOT_FOUND(1201, "数据不存在"),
    DATA_ALREADY_EXIST(1202, "数据已存在"),
    DATA_SAVE_ERROR(1203, "数据保存失败"),
    DATA_UPDATE_ERROR(1204, "数据更新失败"),
    DATA_DELETE_ERROR(1205, "数据删除失败"),
    DATA_IMPORT_ERROR(1206, "数据导入失败"),
    DATA_EXPORT_ERROR(1207, "数据导出失败"),
    DATA_CONCURRENT_MODIFY(1208, "数据已被他人修改，请刷新后重试"),

    /**
     * 系统模块
     */
    SYSTEM_CONFIG_ERROR(1301, "系统配置错误"),
    SYSTEM_MAINTENANCE(1302, "系统维护中"),
    SYSTEM_BUSY(1303, "系统繁忙，请稍后重试"),
    SYSTEM_API_DEPRECATED(1304, "接口已废弃"),
    SYSTEM_VERSION_LOW(1305, "客户端版本过低，请升级"),

    /**
     * 第三方服务
     */
    THIRD_PARTY_SERVICE_ERROR(1401, "第三方服务调用失败"),
    THIRD_PARTY_TIMEOUT(1402, "第三方服务超时"),
    THIRD_PARTY_API_LIMIT(1403, "第三方接口调用次数已达上限"),

    /**
     * 支付模块
     */
    PAYMENT_FAILED(1501, "支付失败"),
    PAYMENT_TIMEOUT(1502, "支付超时"),
    PAYMENT_AMOUNT_ERROR(1503, "支付金额错误"),
    PAYMENT_ORDER_NOT_EXIST(1504, "订单不存在"),
    PAYMENT_ORDER_ALREADY_PAID(1505, "订单已支付"),
    PAYMENT_ORDER_EXPIRED(1506, "订单已过期"),
    PAYMENT_INSUFFICIENT_BALANCE(1507, "余额不足"),
    PAYMENT_REFUND_FAILED(1508, "退款失败"),

    /**
     * 权限模块
     */
    PERMISSION_DENIED(1601, "权限不足"),
    ROLE_NOT_EXIST(1602, "角色不存在"),
    ROLE_ALREADY_EXIST(1603, "角色已存在"),
    PERMISSION_NOT_EXIST(1604, "权限不存在"),

    /**
     * 消息模块
     */
    MESSAGE_SEND_FAILED(1701, "消息发送失败"),
    MESSAGE_TEMPLATE_NOT_FOUND(1702, "消息模板不存在"),
    MESSAGE_RECEIVER_EMPTY(1703, "消息接收人为空"),

    /**
     * 其他
     */
    UNKNOWN_ERROR(9999, "未知错误"),
    CUSTOM_ERROR(9998, "自定义错误");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态码信息
     */
    private final String message;
}
