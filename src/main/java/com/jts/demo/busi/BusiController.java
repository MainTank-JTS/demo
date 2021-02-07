package com.jts.demo.busi;

import com.jts.demo.interceptor.ImportantInterceptor;
import com.jts.demo.mq.disruptor.MessageBo;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/busi")
public class BusiController {

    @Resource
    private BusiService busiService;

    @GetMapping(path = "/index")
    public String busiIndex(String param){
        log.info("invocked BusiController.busiIndex[{}]",param);
        return busiService.busiIndex(param);
    }

}
