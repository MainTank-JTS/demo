package com.jts.demo.proxy;

/**
 * @author nantian
 */
public interface IUserOp {
    /**
     * get user base info
     * @return String user base info
     */
    default String getInfo(){
        return "";
    }
}
