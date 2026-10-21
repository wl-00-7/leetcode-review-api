package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.entity.Problem;
import com.wl.leetcodereviewapi.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return problem.getId();
    }
}
