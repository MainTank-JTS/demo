package com.jts.demo.proxy;

public class User implements IUserOp{

    private String id;
    private String name;

    @Override
    public String getInfo() {
        return id+"_"+name;
    }
}
