package com.wl.leetcodereviewapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodayReviewVO {
    private Long reviewId;
    private Integer round;
    private LocalDate reviewDate;
    private Long problemId;
    private Integer leetcodeNo;
    private String title;
    private String difficulty;
}
