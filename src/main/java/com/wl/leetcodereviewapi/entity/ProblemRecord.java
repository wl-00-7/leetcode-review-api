package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 刷题记录表 record 对应的实体
 * 注：类名不叫 Record，避免和 java.lang.Record 混淆
 */
@Data
public class ProblemRecord {    //刷题纪录表
    Long id;                    //
    Long problemId;             //题目id
    Boolean isAc;               //是否解决
    String reason;              //错因
    String thinking;            //解题思路
    Integer costMinutes;         //耗时（Integer默认为null，耗时0和未填写的区别，不用int）
    LocalDateTime submittedAt; //提交时间

}