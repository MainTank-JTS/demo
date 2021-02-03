package com.jts.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

@Slf4j
@Configuration
public class CustomerAppliacationEnventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent){
            log.info("环境初始化！！！");
        } else if (applicationEvent instanceof ApplicationPreparedEvent){
            log.info("初始化完成！！！");
        } else if (applicationEvent instanceof ContextRefreshedEvent) {
            log.info("应用刷新！！");
        } else if (applicationEvent instanceof ApplicationReadyEvent) {
            log.info("项目启动完成！！");
        } else if (applicationEvent instanceof ContextStartedEvent) {
            log.info("应用启动！！");
        } else if (applicationEvent instanceof ContextStoppedEvent) {
            log.info("项目停止！！");
        } else if (applicationEvent instanceof ContextClosedEvent) {
            log.info("应用关闭！！");
        }
    }
}
