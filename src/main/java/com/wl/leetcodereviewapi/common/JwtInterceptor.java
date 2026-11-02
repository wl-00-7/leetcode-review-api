package com.wl.leetcodereviewapi.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER="Authorization";
    private static final String PREFIX="Bearer ";

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler)throws Exception{
        String header=request.getHeader(HEADER);
        if(header==null||!header.startsWith(PREFIX)){
            writeUnauthorized(response,"未登录");
            return false;
        }
        try{
            String token=header.substring(PREFIX.length());
            Long userId=jwtUtil.parseUserId(token);
            request.setAttribute("userId",userId);
            return true;
        }catch(Exception e){
            writeUnauthorized(response,"登录已过期或凭证无效");
            return false;
        }
    }
    private void writeUnauthorized(HttpServletResponse response,String msg)throws Exception{
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Result.fail(401,msg)));
    }
}
