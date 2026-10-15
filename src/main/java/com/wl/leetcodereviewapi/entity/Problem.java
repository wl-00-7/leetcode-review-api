package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 题目表 problem 对应的实体
 */
@Data
public class Problem {
    Long id;
    Long userId;
    Integer leetcodeNo;
    String title;
    String difficulty;  //EASY / MEDIUM /HARD
    String tags;
    String url;
    LocalDateTime createAt,updateAt;

}