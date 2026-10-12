package com.wl.leetcodereviewapi.controller;

import com.wl.leetcodereviewapi.entity.Problem;
import com.wl.leetcodereviewapi.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 题目接口（当前为验证链路用的最简版本，下一步会抽出 Service 层）
 */
@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

    // 构造器注入（@RequiredArgsConstructor 自动生成带这个字段的构造方法）
    private final ProblemMapper problemMapper;

    /** 查询全部题目 */
    @GetMapping
    public List<Problem> list() {
        return problemMapper.selectAll();
    }
}