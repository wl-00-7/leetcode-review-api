package com.wl.leetcodereviewapi.controller;


import com.wl.leetcodereviewapi.service.ProblemRecordService;
import com.wl.leetcodereviewapi.service.ReviewService;
import com.wl.leetcodereviewapi.dto.TodayReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ProblemRecordService recordService;
    private final ReviewService reviewService;

    @PostMapping("/records")
    public Long record(@RequestParam Long problemId,@RequestParam boolean isAc){
        return recordService.recordProblem(problemId,isAc,null,null,null);
    }

    @GetMapping("/today")
    public List<TodayReviewVO> today(@RequestParam Long userId){
        return reviewService.getTodayReviews(userId);
    }

    @PostMapping("/{id}/done")
    public void done(@PathVariable Long id,@RequestParam boolean passed)
    {
        reviewService.completeReview(id,passed);
    }
}
