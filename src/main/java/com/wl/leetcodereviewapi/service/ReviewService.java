package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.dto.TodayReviewVO;
import com.wl.leetcodereviewapi.entity.Review;
import com.wl.leetcodereviewapi.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;
    public List<TodayReviewVO> getTodayReviews(Long userId){
        return reviewMapper.findDue(userId, LocalDate.now());
    }
    @Transactional
    public void completeReview(Long reviewId,boolean passed){
        Review review=reviewMapper.findById(reviewId);
        if(review==null){
            throw new IllegalArgumentException("复习任务不存在"+reviewId);
        }
        reviewMapper.markDone(reviewId);
        if(!passed){
            reviewMapper.resetRemainingRounds(
                    review.getRecordId(),
                    LocalDate.now().plusDays(3)
            );
        }
    }
}
