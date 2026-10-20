package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.dto.TodayReviewVO;

import com.wl.leetcodereviewapi.entity.Review;
import com.wl.leetcodereviewapi.mapper.ProblemMapper;
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

    @Transactional  //下面的方法有两个往数据库的写操作，此注解保证了两个写操作一起成功，否则回退
    public void completeReview(Long reviewId,boolean passed){
        Review review=reviewMapper.findById(reviewId);
        if(review==null){
            throw new IllegalArgumentException("复习任务不存在"+reviewId);
        }
        //reviewMapper接口的markDone方法，是一个往数据库的写操作
        reviewMapper.markDone(reviewId);
        if(!passed){
            //同上markDone
            reviewMapper.resetRemainingRounds(
                    review.getRecordId(),
                    LocalDate.now().plusDays(3)
            );
        }
    }
}
