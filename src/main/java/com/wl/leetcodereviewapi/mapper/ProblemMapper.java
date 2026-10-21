package com.wl.leetcodereviewapi.mapper;

import com.wl.leetcodereviewapi.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 题目表 problem 对应的 Mapper
 * @Mapper 让 MyBatis 在启动时自动扫描并生成实现类（不需要自己写实现）
 */
@Mapper
public interface ProblemMapper {
    /** 查询全部题目，按 id 倒序 */
    List<Problem> selectAll();
    void insert(Problem problem);
}
