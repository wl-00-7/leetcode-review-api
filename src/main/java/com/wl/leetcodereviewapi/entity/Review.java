package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 复习队列表 review 对应的实体
 */
@Data
public class Review {

    /** 主键 */
    private Long id;

    /** 刷题记录 id */
    private Long recordId;

    /** 复习轮次：1/2/3 对应第 3/7/14 天 */
    private Integer round;

    /** 应复习日期 */
    private LocalDate reviewDate;

    /** 是否已完成：1 是，0 否 */
    private Boolean isDone;

    /** 完成时间 */
    private LocalDateTime doneAt;

    /** 创建时间 */
    private LocalDateTime createdAt;
}