package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 题目表 problem 对应的实体
 */
@Data
public class Problem {      //题目表
    Long id;                   //题目id
    Long userId;                 //用户id
    Integer leetcodeNo;         //lc题号
    String title;               //题目
    String difficulty;  //难度EASY / MEDIUM /HARD
    String tags;            //标签（树，BFS，二分查找）
    String url;             //链接
    LocalDateTime createdAt, updatedAt;  //创建时间、更新时间

}