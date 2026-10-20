package com.wl.leetcodereviewapi.mapper;

import com.wl.leetcodereviewapi.entity.ProblemRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 刷题记录表 record 对应的 Mapper
 * @Mapper 让 MyBatis 在启动时自动扫描并生成实现类（不需要自己写实现）
 */
@Mapper
public interface ProblemRecordMapper {
    void insert(ProblemRecord record);
}