-- ============================================================
-- leetcode-review-api 建库建表脚本
-- 数据库：MySQL 8.4
-- 说明：本脚本可重复执行（IF NOT EXISTS），不会覆盖已有数据
-- ============================================================

CREATE DATABASE IF NOT EXISTS leetcode_review
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE leetcode_review;

-- ------------------------------------------------------------
-- 用户表
-- 说明：v1 暂时不做登录功能，先建表 + 插一条演示数据，给 problem.user_id 用
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    username    VARCHAR(50)     NOT NULL                COMMENT '用户名',
    password    VARCHAR(100)    NOT NULL                COMMENT '密码（后续需加密存储）',
    nickname    VARCHAR(50)     DEFAULT NULL            COMMENT '昵称',
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';

-- ------------------------------------------------------------
-- 题目表
-- 说明：标签先用单字段逗号分隔（如 "BFS,二叉树"），后续可升级为标签表 + 中间表
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS problem (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id      BIGINT UNSIGNED NOT NULL                COMMENT '归属用户 id',
    leetcode_no  INT             DEFAULT NULL            COMMENT 'LeetCode 题号，如 105',
    title        VARCHAR(200)    NOT NULL                COMMENT '题目标题',
    difficulty   VARCHAR(10)     NOT NULL DEFAULT 'MEDIUM' COMMENT '难度：EASY / MEDIUM / HARD',
    tags         VARCHAR(255)    DEFAULT NULL            COMMENT '标签，逗号分隔，如 "BFS,二叉树"',
    url          VARCHAR(300)    DEFAULT NULL            COMMENT '原题链接',
    created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_user_id (user_id),
    KEY idx_difficulty (difficulty)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '题目表';

-- ------------------------------------------------------------
-- 刷题记录表
-- 说明：每刷一次题产生一条记录，是复习任务的来源
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS record (
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    problem_id    BIGINT UNSIGNED NOT NULL                COMMENT '题目 id',
    is_ac         TINYINT         NOT NULL DEFAULT 0      COMMENT '本次是否 AC：1 是，0 否',
    reason        VARCHAR(500)    DEFAULT NULL            COMMENT '错因',
    thinking      TEXT                                    COMMENT '解题思路',
    cost_minutes  INT             DEFAULT NULL            COMMENT '耗时（分钟）',
    submitted_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    PRIMARY KEY (id),
    KEY idx_problem_id (problem_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '刷题记录表';

-- ------------------------------------------------------------
-- 复习队列表
-- 说明：核心业务表。一条记录派生 3 条复习任务，round 1/2/3 对应第 3/7/14 天
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS review (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    record_id    BIGINT UNSIGNED NOT NULL                COMMENT '刷题记录 id',
    round        TINYINT         NOT NULL DEFAULT 1      COMMENT '复习轮次：1/2/3 对应 3/7/14 天',
    review_date  DATE            NOT NULL                COMMENT '应复习日期',
    is_done      TINYINT         NOT NULL DEFAULT 0      COMMENT '是否已完成：1 是，0 否',
    done_at      DATETIME        DEFAULT NULL            COMMENT '完成时间',
    created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY idx_record_id (record_id),
    KEY idx_review_date (review_date, is_done)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '复习队列表';

-- ------------------------------------------------------------
-- 演示数据：一条用户记录（v1 无登录，固定用它）
-- ------------------------------------------------------------
INSERT INTO `user` (username, password, nickname)
SELECT 'wl', '123456', 'wl'
WHERE NOT EXISTS (SELECT 1 FROM `user` WHERE username = 'wl');

-- ============================================================
-- 关于为什么没有加物理外键（FOREIGN KEY）：
-- 大厂规范通常禁止物理外键（并发写时锁开销大、分库分表后会失效），
-- 关联完整性由应用层（Service）保证。面试问到时这是个加分点。
-- ============================================================