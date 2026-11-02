package com.wl.leetcodereviewapi.config;

import com.wl.leetcodereviewapi.common.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor)  //注册拦截器
                .addPathPatterns("/**")        //拦截所有路径
                .excludePathPatterns("/auth/**");  //放行/auth/**登录
    }
}
