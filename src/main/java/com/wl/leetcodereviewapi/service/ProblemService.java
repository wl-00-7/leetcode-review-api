package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.entity.Problem;
import com.wl.leetcodereviewapi.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemMapper problemMapper;
    public List<Problem> listAll(){
        return problemMapper.selectAll();
    }
    public Long create(Problem problem){
        if(problem.getDifficulty()==null){
            problem.setDifficulty("MEDIUM");
        }
        problemMapper.insert(problem);
        log.info("创建题目：id={},title={}",problem.getId(),problem.getTitle());
        return problem.getId();
    }
    public void update(Long id,Problem problem){
        problem.setId(id);
        if(problem.getDifficulty()==null){
            problem.setDifficulty("MEDIUM");
        }
        int rows=problemMapper.update(problem);
        if(rows==0){
            throw new IllegalArgumentException("题目不存在:"+id);

        }
        log.info("修改题目：id={}",id);
    }
    public void delete(Long id){
        int rows=problemMapper.delete(id);
        if(rows==0){
            throw new IllegalArgumentException("题目不存在"+id);
        }
        log.info("删除题目：{}",id);
    }
}
