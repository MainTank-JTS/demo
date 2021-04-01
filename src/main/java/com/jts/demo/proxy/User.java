package com.jts.demo.proxy;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jts
 */
@Data
public class User implements IUserOp{

    private String id;
    private String name;

    @Override
    public String getInfo() {
        return id+"_"+name;
    }
}
