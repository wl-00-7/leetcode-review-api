package com.wl.leetcodereviewapi.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 题目表 problem 对应的实体
 */
//@NotNull只看有没有     null-拒绝   ""空串-通过  ""全空白-通过  适用数字，对象
//@NotEmpty看有没有内容   null-拒绝  ""空串-拒绝   ""-通过       适用于集合
//@NotBlank看有没有真内容  null-拒绝 ""空串-拒绝    ""-拒绝     适用于字符串
@Data
public class Problem {      //题目表
    Long id;                   //题目id
    @NotNull(message="用户id不能为空")
    Long userId;                 //用户id
    Integer leetcodeNo;         //lc题号
    @NotBlank(message="题目标题不能为空")
    String title;               //题目
    String difficulty;  //难度EASY / MEDIUM /HARD
    String tags;            //标签（树，BFS，二分查找）
    String url;             //链接
    LocalDateTime createdAt, updatedAt;  //创建时间、更新时间

}