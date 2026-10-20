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
//1-12导入包
@Service                               //登记进Spring容器
@RequiredArgsConstructor          //生成构造器，给final字段赋值
public class ProblemRecordService {
    //两个工具类，但是没有new他们
    // 是Spring启动时从容器中找到这两个Mapper，通过构造器塞进来
    //final一旦注入就不能修改（安全）
    private final ProblemRecordMapper recordMapper;
    private final ReviewMapper reviewMapper;
//为什么推荐构造器注入，而不是字段上的@Autowired
//1final不可变  2对象创建时就注入了，不会出现半成品对象  3单元测试可以直接new
//final锁的是变量的指向，修饰基本类型时，不可改，修饰引用类型时，如数组，数组不可改，但是数组里的内容可以改
    private static final int[] REVIEW_INTERVALS={3,7,14};
//为什么用数组加循环
//如果需要修改，只用改这一行
// static=属于类，不属于某个对象（所有实例共用这一份）  // final不能修改值

    @Transactional   //保证原子性，
    public Long recordProblem(Long problemId,boolean isAc,String reason,String thinking,Integer costMinutes){
//为什么用Integer包装类不用int
//Integer允许null，int默认是0（耗时0分钟和没填写的区别），凡是可选参数，都用包装类
        ProblemRecord record =new ProblemRecord();
        record.setProblemId(problemId);
        record.setIsAc(isAc);
        record.setReason(reason);
        record.setThinking(thinking);
        record.setCostMinutes(costMinutes);
        record.setSubmittedAt(LocalDateTime.now());
        //当前服务器时间  LocalDateTime.now()
        recordMapper.insert(record);  //存进数据库  真正的写操作
        //主键回填，这一行写入后，就可以record.getId（）就有值了
//recordMapper只是个接口，写了insert方法，但是并没有实现
//实现是由MyBatis生成的————读取了ProblemRecordMapper.xml里的<insert>
//MyBatis通过@Mapper注解+XML，用动态代理在运行时生成实现类，注册进Spring
        LocalDate today=LocalDate.now();
        //生成三条复习任务
        for(int i=0;i<REVIEW_INTERVALS.length;i++){
            Review review=new Review();
        //record.getId（）关联到problemrecord的id
            review.setRecordId(record.getId());
            
            review.setRound(i+1);//三轮循环1/2/3
            review.setReviewDate(today.plusDays(REVIEW_INTERVALS[i]));//对应日期
            review.setIsDone(false);
            reviewMapper.insert(review);
        }
        return record.getId();
    }
}
