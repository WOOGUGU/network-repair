package com.example.repair.config;

import com.example.repair.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptorConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")//添加要拦截哪些
//                .excludePathPatterns(
//                        "/login/viceadminister", "/", "/login/**", "/css/**", "/img/**", "/js/**",
//                        "/administer/**", "/student/**", "/maintainer/**", "/test/**"
//                );
//    }
}
