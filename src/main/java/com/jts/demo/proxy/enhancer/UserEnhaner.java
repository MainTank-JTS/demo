package com.jts.demo.proxy.enhancer;

import com.jts.demo.proxy.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author jts
 */
@Slf4j
@Component
public class UserEnhaner {
    public User newUser(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            log.info("Use [{}],invock [{}]",UserEnhaner.class.getName(),method.getName());
            return methodProxy.invokeSuper(o,objects);
        });
        return (User)enhancer.create();
    }
}
