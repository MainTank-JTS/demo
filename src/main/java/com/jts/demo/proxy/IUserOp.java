package com.jts.demo.proxy;

public interface IUserOp {
    default String getInfo(){
        return "";
    }
}
