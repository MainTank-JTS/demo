package com.jts.demo.proxy;

/**
 * @author jts
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
