package com.wl.leetcodereviewapi.mapper;

import com.wl.leetcodereviewapi.dto.TodayReviewVO;
import com.wl.leetcodereviewapi.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface ReviewMapper {
    void insert(Review review);
    List<TodayReviewVO> findDue(@Param("userId") long userId, @Param("today") LocalDate today);

    void markDone(@Param("reviewId") Long reviewId);
    Review findById(@Param("reviewId") Long reviewId);

    void resetRemainingRounds(@Param("recordId") Long recordId,@Param("newDate") LocalDate newDate);
}
