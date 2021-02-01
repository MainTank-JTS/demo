package com.jts.demo.config;

import com.jts.demo.interceptor.UseTimeHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @PostConstruct
    public void init() {
        log.info("[{}] exec init method", WebConfig.class.getName());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UseTimeHandlerInterceptor());
        log.info("Add interceptor[{}]", UseTimeHandlerInterceptor.class.getName());
    }
}
