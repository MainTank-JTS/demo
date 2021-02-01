package com.jts.demo.busi;

import com.jts.demo.interceptor.ImportantInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusiService {

    @ImportantInterceptor("getStr")
    public String busiIndex(String param){
        log.info("invocked BusiService.busiIndex[{}]",param);
        String res = "busiIndex_"+param;
        log.info("res BusiService.busiIndex[{}]",res);
        return res;
    }
}
