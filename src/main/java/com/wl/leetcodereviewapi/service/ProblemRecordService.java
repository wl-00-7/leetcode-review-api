package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.entity.ProblemRecord;
import com.wl.leetcodereviewapi.entity.Review;
import com.wl.leetcodereviewapi.mapper.ProblemRecordMapper;
import com.wl.leetcodereviewapi.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProblemRecordService {

    private final ProblemRecordMapper recordMapper;
    private final ReviewMapper reviewMapper;

    private static final int[] REVIEW_INTERVALS={3,7,14};

    @Transactional
    public Long recordProblem(Long problemId,boolean isAc,String reason,String thinking,Integer costMinutes){
        ProblemRecord record =new ProblemRecord();
        record.setProblemId(problemId);
        record.setIsAc(isAc);
        record.setReason(reason);
        record.setThinking(thinking);
        record.setCostMinutes(costMinutes);
        record.setSubmittedAt(LocalDateTime.now());
        recordMapper.insert(record);

        LocalDate today=LocalDate.now();
        for(int i=0;i<REVIEW_INTERVALS.length;i++){
            Review review=new Review();
            review.setRecordId(record.getId());
            review.setRound(i+1);
            review.setReviewDate(today.plusDays(REVIEW_INTERVALS[i]));
            review.setIsDone(false);
            reviewMapper.insert(review);
        }
        return record.getId();
    }
}
