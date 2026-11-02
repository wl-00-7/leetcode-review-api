package com.wl.leetcodereviewapi.service;

import com.wl.leetcodereviewapi.common.JwtUtil;
import com.wl.leetcodereviewapi.entity.User;
import com.wl.leetcodereviewapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public String login(String username,String password){
        //申请用户对象的创建（这里应该是类似通行证吧）
        User user=userMapper.findByUsername(username);
        //登陆验证   失败时统一回复防止泄露
        if(user==null||!user.getPassword().equals(password)){
            //进入if 通过throw抛出异常是不是直接就结束了，下面的log.info是不是不执行了？
            throw new IllegalArgumentException("用户名或密码错误");
        }
        //登陆成功时的执行？  {}是占位符 日志级别不开就不拼接 省性能
    log.info("用户登录成功：id={},username={}",user.getId(),user.getUsername());
        //交给JwtUtil生成token，返回给Controller
        return jwtUtil.generate(user.getId());
    }

}
