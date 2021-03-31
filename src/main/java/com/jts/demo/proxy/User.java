package com.jts.demo.proxy;

import lombok.Builder;

/**
 * @author jts
 */
@Builder
public class User implements IUserOp{

    private final String id;
    private final String name;

    @Override
    public String getInfo() {
        return id+"_"+name;
    }
}
