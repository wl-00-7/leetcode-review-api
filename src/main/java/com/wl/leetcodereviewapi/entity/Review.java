package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 复习队列表 review 对应的实体
 */
@Data
public class Review {
    Long id;
    Long recordId;
    Integer round;  //1/2/3=第3/7/14天
    LocalDate reviewDate;
    Boolean isDone;
    LocalDateTime doneAt;
    LocalDateTime creatAt;

}