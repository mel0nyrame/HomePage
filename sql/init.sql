CREATE DATABASE IF NOT EXISTS `homepage`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE homepage;

CREATE TABLE homepage_user
(
    `id`          BIGINT       NOT NULL COMMENT '用户ID',
    `nickname`    VARCHAR(64)  NOT NULL COMMENT '用户昵称',
    `username`    VARCHAR(32) UNIQUE COMMENT '用户账号',
    `email`       VARCHAR(64) UNIQUE COMMENT '邮箱',
    `password`    VARCHAR(256) NOT NULL COMMENT '用户密码',
    `enabled`     TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '是否启用 [0=禁用, 1=启用]',
    `authorities` VARCHAR(256) NOT NULL DEFAULT 'USER' COMMENT '用户权限',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_nickname` (`nickname`),
    UNIQUE KEY `uk_email` (`email`),
    KEY `idx_enabled` (`enabled`),
    KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '用户表';

CREATE TABLE homepage_admin
(
    `id`          BIGINT       NOT NULL COMMENT '管理员ID',
    `nickname`    VARCHAR(64)  NOT NULL COMMENT '管理员昵称',
    `account`     VARCHAR(32) UNIQUE COMMENT '管理员账号',
    `password`    VARCHAR(256) NOT NULL COMMENT '管理员密码',
    `enabled`     TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '是否启用 [0=禁用, 1=启用]',
    `authorities` VARCHAR(256) NOT NULL DEFAULT 'ADMIN' COMMENT '管理员权限',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_account` (`account`),
    KEY `idx_nickname` (`nickname`),
    KEY `idx_enabled` (`enabled`),
    KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '管理员表';

CREATE TABLE homepage_login_log
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`    BIGINT       NOT NULL COMMENT '用户/管理员id',
    `login_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登陆时间',
    `login_ip`   VARCHAR(64)  NOT NULL COMMENT '登陆ip',
    `user_agent` VARCHAR(512) NOT NULL COMMENT '特征头',
    `device_os`  VARCHAR(64)  NOT NULL COMMENT '设备系统',
    `location`   VARCHAR(64)  NOT NULL COMMENT '登陆地点',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_ip` (`login_ip`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '登陆记录表';