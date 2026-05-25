CREATE DATABASE IF NOT EXISTS `homepage`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE homepage;

CREATE TABLE homepage_user
(
    `id`          BIGINT UNIQUE AUTO_INCREMENT COMMENT '用户id',
    `nickname`    VARCHAR(64)  NOT NULL COMMENT '用户昵称',
    `username`    VARCHAR(32) UNIQUE COMMENT '用户账号',
    `email`       VARCHAR(32) UNIQUE COMMENT '邮箱',
    `password`    VARCHAR(256) NOT NULL COMMENT '用户密码',
    `enabled`     TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '是否启用',
    `authorities` VARCHAR(255) DEFAULT 'USER' COMMENT '用户权限',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE homepage_admin
(
    `id`          BIGINT UNIQUE AUTO_INCREMENT COMMENT '管理员id',
    `account`     VARCHAR(32) UNIQUE COMMENT '管理员账号',
    `password`    VARCHAR(256) NOT NULL COMMENT '用户密码',
    `enabled`     TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '是否启用',
    `authorities` VARCHAR(255)          DEFAULT 'ADMIN' COMMENT '管理员权限',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_account` (`account`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;