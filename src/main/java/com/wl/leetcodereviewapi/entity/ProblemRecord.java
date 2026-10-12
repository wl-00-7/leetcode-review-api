package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 刷题记录表 record 对应的实体
 * 注：类名不叫 Record，避免和 java.lang.Record 混淆
 */
@Data
public class ProblemRecord {

    /** 主键 */
    private Long id;

    /** 题目 id */
    private Long problemId;

    /** 本次是否 AC：1 是，0 否 */
    private Boolean isAc;

    /** 错因 */
    private String reason;

    /** 解题思路 */
    private String thinking;

    /** 耗时（分钟） */
    private Integer costMinutes;

    /** 提交时间 */
    private LocalDateTime submittedAt;
}