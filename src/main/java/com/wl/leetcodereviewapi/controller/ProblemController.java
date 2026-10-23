package com.wl.leetcodereviewapi.controller;

import com.wl.leetcodereviewapi.common.Result;
import com.wl.leetcodereviewapi.entity.Problem;
import com.wl.leetcodereviewapi.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目接口
 */
@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

    // 构造器注入（@RequiredArgsConstructor 自动生成带这个字段的构造方法）
    private final ProblemService problemService;

    /** 查询全部题目，按 id 倒序 */
    @GetMapping
    public Result<List<Problem>> listAll() {
        return Result.ok(problemService.listAll());
    }

    @PostMapping
    public Result<Long> create(@RequestBody Problem problem){
        return Result.ok(problemService.create(problem));
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id,@RequestBody Problem problem){
        problemService.update(id,problem);
        return Result.ok(null);
    }
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id){
        problemService.delete(id);
        return Result.ok(null);
    }

}
