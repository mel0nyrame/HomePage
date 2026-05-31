package com.homepage.common.constant;

public final class MqConstants {

    private MqConstants() {}

    /** 用户注册事件 — Exchange */
    public static final String USER_EXCHANGE = "homepage.user.exchange";

    /** 用户注册事件 — Queue */
    public static final String USER_REGISTERED_QUEUE = "homepage.user.registered.queue";

    /** 用户注册事件 — Routing Key */
    public static final String USER_REGISTERED_ROUTING_KEY = "homepage.user.registered";
}
