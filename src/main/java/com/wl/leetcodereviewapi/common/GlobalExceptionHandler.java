package com.wl.leetcodereviewapi.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//@ControllerAdvice+@ResponseBody
@RestControllerAdvice   //全局拦截所有controler的异常（返回值自动转JSON）
public class GlobalExceptionHandler {
    //业务参数问题404
    //只接收这类异常 Spring会选最具体的那个--所以IllegalArgumentException不会被下面的Exception Handler抢走
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result<Void>> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(404).body(Result.fail(404,e.getMessage()));
    }
    //其余所有异常500->不暴露内部细节
    //ResopnseEntity 同时控制状态码和响应体，只返回Result的话，状态码永远是200
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleUnknown(Exception e){
        return ResponseEntity.status(500).body(Result.fail(500,"服务器内部错误"));
    }
}
