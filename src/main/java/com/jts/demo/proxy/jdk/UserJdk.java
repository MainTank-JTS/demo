package com.jts.demo.proxy.jdk;

import com.jts.demo.proxy.IUserOp;
import com.jts.demo.proxy.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
@Component
public class UserJdk implements InvocationHandler {

    private IUserOp target;

    public void bind(IUserOp target){
        this.target = target;
    }

    public IUserOp newUser(){
        return (IUserOp) Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{IUserOp.class},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Use [{}],invock [{}]", UserJdk.class.getName(),method.getName());
        Object res = method.invoke(target,args);
        return res;
    }
}
