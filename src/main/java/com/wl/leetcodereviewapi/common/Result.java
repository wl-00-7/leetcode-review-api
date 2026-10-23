package com.wl.leetcodereviewapi.common;

import lombok.Data;

//统一响应封装
//code：0表示成功，非0表示业务错误码
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;  //T表示泛型
    //Result<T>表示Result<Problem>,Result<List<Problem>>都能用同一个类
    //static <T> Result<T> ok(...)静态工厂方法，不罗嗦（不会）
    public static <T> Result<T> ok(T data){
        Result<T> r=new Result<>();
        r.setCode(0);   //设置code=0表示成功
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    public static <T> Result<T> fail(int code,String message){
        Result<T> r=new Result<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }
}
