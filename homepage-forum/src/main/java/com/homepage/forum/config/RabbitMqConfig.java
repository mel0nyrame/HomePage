package com.homepage.forum.config;

import com.homepage.common.constant.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    /** Topic 交换机 — 用户事件 */
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(MqConstants.USER_EXCHANGE, true, false);
    }

    /** 用户注册队列 */
    @Bean
    public Queue userRegisteredQueue() {
        return QueueBuilder.durable(MqConstants.USER_REGISTERED_QUEUE).build();
    }

    /** 绑定队列到交换机 */
    @Bean
    public Binding userRegisteredBinding() {
        return BindingBuilder
                .bind(userRegisteredQueue())
                .to(userExchange())
                .with(MqConstants.USER_REGISTERED_ROUTING_KEY);
    }
}
