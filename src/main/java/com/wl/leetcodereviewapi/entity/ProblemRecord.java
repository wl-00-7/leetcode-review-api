package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 刷题记录表 record 对应的实体
 * 注：类名不叫 Record，避免和 java.lang.Record 混淆
 */
@Data
public class ProblemRecord {
    Long id;
    Long problemId;
    Boolean isAc;
    String reason;
    String thinking;
    Integer costMinutes;
    LocalDateTime submittedAt;

}