package com.homepage.common.model.event;

import java.io.Serializable;

/**
 * 用户注册事件 — auth模块发送，forum模块消费。
 */
public record UserRegisteredEvent(
        Long userId,
        String username,
        String nickname
) implements Serializable {
}
