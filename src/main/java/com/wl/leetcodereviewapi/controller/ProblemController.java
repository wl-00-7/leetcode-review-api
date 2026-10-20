package com.wl.leetcodereviewapi.controller;

import com.wl.leetcodereviewapi.entity.Problem;
import com.wl.leetcodereviewapi.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Problem> listAll() {
        return problemService.listAll();
    }

}
