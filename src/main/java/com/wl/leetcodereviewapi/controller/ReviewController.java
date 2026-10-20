package com.wl.leetcodereviewapi.controller;


import com.wl.leetcodereviewapi.service.ProblemRecordService;
import com.wl.leetcodereviewapi.service.ReviewService;
import com.wl.leetcodereviewapi.dto.TodayReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//为什么从https->controller->service->mapper之间都是单向的，下层永远不知道上层
//解耦：以后换数据库，只改Mapper，其他的不用动
//可测试:Service可以注入一个假Mapper单测，不用真连接数据库
//职责单一：每一层只干一件事，改一处不影响其他


//=@Controller+@ResponseBody  这个类接受HTTP请求，返回值直接当数据自动转JSON，不是跳转页面
@RestController
//类级路径前缀  这个类下的所欲接口的URL都挂在/review下面
@RequestMapping("/reviews")
//生成构造器，注入下面的两个Service
@RequiredArgsConstructor
public class ReviewController {
    //controller不自己干活，持有service，把活转出去
    private final ProblemRecordService recordService;
    private final ReviewService reviewService;
//方法级映射 只接受POST请求  完整路径是/reviews/records
    @PostMapping("/records")
    public Long record(@RequestParam Long problemId,@RequestParam boolean isAc){
    //@RequestParam从URL的key=value里面取参数
        return recordService.recordProblem(problemId,isAc,null,null,null);
    }
//只接受GET    GET /reviews/today?userId=1
    @GetMapping("/today")
    public List<TodayReviewVO> today(@RequestParam Long userId){
        return reviewService.getTodayReviews(userId);
    }
//dto类
    //{id}占位符
    @PostMapping("/{id}/done")
    public void done(@PathVariable Long id,@RequestParam boolean passed)
    {
   //@PathVariable是从路径段里取值  POST/reviews/4/done中的4
    //@RequestParam boolean passed从？passed=false取值  URL问好后面/表单
        reviewService.completeReview(id,passed);
    }
}
