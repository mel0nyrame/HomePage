CREATE DATABASE IF NOT EXISTS `homepage`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE homepage;

CREATE TABLE homepage_user
(
    `id`          BIGINT       NOT NULL COMMENT '用户ID',
    `nickname`    VARCHAR(64)  NOT NULL COMMENT '用户昵称',
    `username`    VARCHAR(32)  UNIQUE COMMENT '用户账号',
    `email`       VARCHAR(64)  UNIQUE COMMENT '邮箱',
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
    `account`     VARCHAR(32)  UNIQUE COMMENT '管理员账号',
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

CREATE TABLE forum_user_profile
(
    `id`              BIGINT       NOT NULL COMMENT '档案ID',
    `username`        VARCHAR(32)  NOT NULL COMMENT '用户名，来自auth模块',
    `nickname`        VARCHAR(64)  NOT NULL COMMENT '展示名',
    `avatar`          VARCHAR(512)          COMMENT '头像URL',
    `bio`             VARCHAR(256)          COMMENT '个人简介',
    `signature`       VARCHAR(256)          COMMENT '签名',
    `post_count`      INT          NOT NULL DEFAULT 0 COMMENT '帖子数',
    `follower_count`  INT          NOT NULL DEFAULT 0 COMMENT '粉丝数',
    `following_count` INT          NOT NULL DEFAULT 0 COMMENT '关注数',
    `create_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_nickname` (`nickname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛用户资料表';

CREATE TABLE forum_board
(
    `id`          BIGINT       NOT NULL COMMENT '板块ID',
    `name`        VARCHAR(32)  NOT NULL COMMENT '板块名',
    `description` VARCHAR(256)          COMMENT '描述',
    `sort_order`  INT          NOT NULL DEFAULT 0 COMMENT '排序权重',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛板块表';

CREATE TABLE forum_post
(
    `id`              BIGINT       NOT NULL COMMENT '帖子ID',
    `author_username` VARCHAR(32)  NOT NULL COMMENT '作者用户名',
    `board_id`        BIGINT       NOT NULL COMMENT '板块ID',
    `title`           VARCHAR(128) NOT NULL COMMENT '标题',
    `content`         TEXT                  COMMENT 'Markdown原文',
    `status`          TINYINT      NOT NULL DEFAULT 1 COMMENT '1=发布 2=草稿 3=已删除',
    `is_pinned`       TINYINT      NOT NULL DEFAULT 0 COMMENT '置顶',
    `is_essence`      TINYINT      NOT NULL DEFAULT 0 COMMENT '加精',
    `edit_time`       DATETIME              COMMENT '最后编辑时间',
    `view_count`      INT          NOT NULL DEFAULT 0 COMMENT '浏览数',
    `like_count`      INT          NOT NULL DEFAULT 0 COMMENT '赞数',
    `dislike_count`   INT          NOT NULL DEFAULT 0 COMMENT '踩数',
    `bookmark_count`  INT          NOT NULL DEFAULT 0 COMMENT '收藏数',
    `comment_count`   INT          NOT NULL DEFAULT 0 COMMENT '评论数',
    `create_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_author_username` (`author_username`),
    KEY `idx_board_id` (`board_id`),
    KEY `idx_status` (`status`),
    KEY `idx_is_pinned` (`is_pinned`),
    KEY `idx_is_essence` (`is_essence`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_like_count` (`like_count`),
    KEY `idx_view_count` (`view_count`),
    KEY `idx_bookmark_count` (`bookmark_count`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛帖子表';

CREATE TABLE forum_tag
(
    `id`           BIGINT      NOT NULL COMMENT '标签ID',
    `name`         VARCHAR(32) NOT NULL COMMENT '标签名（小写归一）',
    `type`         TINYINT     NOT NULL DEFAULT 2 COMMENT '1=管理员预设 2=用户创建',
    `audit_status` TINYINT     NOT NULL DEFAULT 1 COMMENT '1=待审核 2=已通过 3=已拒绝',
    `create_time`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛标签表';

CREATE TABLE forum_post_tag
(
    `id`      BIGINT   NOT NULL COMMENT '关联ID',
    `post_id` BIGINT   NOT NULL COMMENT '帖子ID',
    `tag_id`  BIGINT   NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_post_tag` (`post_id`, `tag_id`),
    KEY `idx_tag_id` (`tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '帖子标签关联表';

CREATE TABLE forum_comment
(
    `id`              BIGINT       NOT NULL COMMENT '评论ID',
    `post_id`         BIGINT       NOT NULL COMMENT '所属帖子ID',
    `author_username` VARCHAR(32)  NOT NULL COMMENT '作者用户名',
    `parent_id`       BIGINT                COMMENT '父评论ID（NULL=一级评论）',
    `root_id`         BIGINT                COMMENT '根评论ID（NULL=一级评论）',
    `content`         TEXT         NOT NULL COMMENT 'Markdown内容',
    `status`          TINYINT      NOT NULL DEFAULT 1 COMMENT '1=正常 2=已删除',
    `like_count`      INT          NOT NULL DEFAULT 0 COMMENT '赞数',
    `dislike_count`   INT          NOT NULL DEFAULT 0 COMMENT '踩数',
    `reply_count`     INT          NOT NULL DEFAULT 0 COMMENT '子回复数',
    `create_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_post_id` (`post_id`),
    KEY `idx_author_username` (`author_username`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_root_id` (`root_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛评论表';

CREATE TABLE forum_like_record
(
    `id`          BIGINT      NOT NULL COMMENT '记录ID',
    `username`    VARCHAR(32) NOT NULL COMMENT '用户用户名',
    `target_type` TINYINT     NOT NULL COMMENT '1=帖子 2=评论',
    `target_id`   BIGINT      NOT NULL COMMENT '目标ID',
    `type`        TINYINT     NOT NULL COMMENT '1=赞 2=踩',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_target` (`username`, `target_type`, `target_id`),
    KEY `idx_target` (`target_type`, `target_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛点赞记录表';

CREATE TABLE forum_bookmark_folder
(
    `id`          BIGINT      NOT NULL COMMENT '收藏夹ID',
    `username`    VARCHAR(32) NOT NULL COMMENT '用户用户名',
    `name`        VARCHAR(32) NOT NULL COMMENT '收藏夹名',
    `is_public`   TINYINT     NOT NULL DEFAULT 0 COMMENT '0=私密 1=公开',
    `sort_order`  INT         NOT NULL DEFAULT 0 COMMENT '排序权重',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛收藏夹表';

CREATE TABLE forum_bookmark
(
    `id`          BIGINT      NOT NULL COMMENT '收藏ID',
    `username`    VARCHAR(32) NOT NULL COMMENT '用户用户名',
    `post_id`     BIGINT      NOT NULL COMMENT '帖子ID',
    `folder_id`   BIGINT      NOT NULL COMMENT '收藏夹ID',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_post` (`username`, `post_id`),
    KEY `idx_folder_id` (`folder_id`),
    KEY `idx_post_id` (`post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛收藏记录表';

CREATE TABLE forum_follow
(
    `id`                BIGINT      NOT NULL COMMENT '关注ID',
    `follower_username` VARCHAR(32) NOT NULL COMMENT '关注者用户名',
    `followee_username` VARCHAR(32) NOT NULL COMMENT '被关注者用户名',
    `create_time`       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_follower_followee` (`follower_username`, `followee_username`),
    KEY `idx_follower` (`follower_username`),
    KEY `idx_followee` (`followee_username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛关注表';

CREATE TABLE forum_notification
(
    `id`          BIGINT       NOT NULL COMMENT '通知ID',
    `username`    VARCHAR(32)  NOT NULL COMMENT '接收人用户名',
    `type`        TINYINT      NOT NULL COMMENT '1=回复 2=点赞 3=踩 4=关注 5=系统通知 6=私信 7=收藏 8=审核结果',
    `title`       VARCHAR(128) NOT NULL COMMENT '通知标题',
    `content`     VARCHAR(512)          COMMENT '通知内容',
    `is_read`     TINYINT      NOT NULL DEFAULT 0 COMMENT '0=未读 1=已读',
    `related_id`  BIGINT                COMMENT '关联的帖子/评论/私信ID',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`),
    KEY `idx_username_read` (`username`, `is_read`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛通知表';

CREATE TABLE forum_report
(
    `id`                BIGINT       NOT NULL COMMENT '举报ID',
    `reporter_username` VARCHAR(32)  NOT NULL COMMENT '举报人用户名',
    `target_type`       TINYINT      NOT NULL COMMENT '1=帖子 2=评论 3=私信',
    `target_id`         BIGINT       NOT NULL COMMENT '目标ID',
    `reason`            VARCHAR(32)  NOT NULL COMMENT '举报原因（垃圾广告/人身攻击/违规内容/其他）',
    `description`       VARCHAR(512)          COMMENT '补充说明',
    `create_time`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_reporter_target` (`reporter_username`, `target_type`, `target_id`),
    KEY `idx_target` (`target_type`, `target_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛举报表';

CREATE TABLE forum_message
(
    `id`                BIGINT      NOT NULL COMMENT '消息ID',
    `sender_username`   VARCHAR(32) NOT NULL COMMENT '发送人用户名',
    `receiver_username` VARCHAR(32) NOT NULL COMMENT '接收人用户名',
    `content`           TEXT        NOT NULL COMMENT '消息内容',
    `is_read`           TINYINT     NOT NULL DEFAULT 0 COMMENT '0=未读 1=已读',
    `create_time`       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_sender_receiver` (`sender_username`, `receiver_username`),
    KEY `idx_receiver_read` (`receiver_username`, `is_read`),
    KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT = '论坛私信表';
