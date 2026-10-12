package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 题目表 problem 对应的实体
 */
@Data
public class Problem {

    /** 主键 */
    private Long id;

    /** 归属用户 id */
    private Long userId;

    /** LeetCode 题号，如 105 */
    private Integer leetcodeNo;

    /** 题目标题 */
    private String title;

    /** 难度：EASY / MEDIUM / HARD */
    private String difficulty;

    /** 标签，逗号分隔，如 "BFS,二叉树" */
    private String tags;

    /** 原题链接 */
    private String url;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}