package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.dto.TodayReviewVO;
import com.wl.leetcodereviewapi.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;
    public List<TodayReviewVO> getTodayReviews(Long userId){
        return reviewMapper.findDue(userId, LocalDate.now());
    }
    public void completeReview(Long reviewId){
        reviewMapper.markDone(reviewId);
    }
}
