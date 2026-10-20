package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 复习队列表 review 对应的实体
 */
@Data
public class Review { //复习队列表
    Long id;        //主键
    Long recordId;      //指向题目idProblemRecord
    Integer round;  //1/2/3=第3/7/14天
    LocalDate reviewDate;//复习时间  localdate是年月日
    Boolean isDone;  //是否复习
    LocalDateTime doneAt;  //完成时间   这个是有时有分，更精确
    LocalDateTime createdAt;  //创建时间

}