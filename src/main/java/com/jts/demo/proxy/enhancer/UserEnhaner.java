package com.jts.demo.proxy.enhancer;

import com.jts.demo.proxy.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
public class UserEnhaner {
    public User newUser(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                log.info("Use [{}],invock [{}]",UserEnhaner.class.getName(),method.getName());
                Object res = methodProxy.invokeSuper(o,objects);
                return res;
            }
        });
        return (User)enhancer.create();
    }
}
