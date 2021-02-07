package com.jts.demo.busi;

import com.jts.demo.interceptor.ImportantInterceptor;
import com.jts.demo.mq.disruptor.MessageBo;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BusiService {

    @Resource
    private RingBuffer<MessageBo> messageBoRingBuffer;

    @ImportantInterceptor("getStr")
    public String busiIndex(String param){
        log.info("invocked BusiService.busiIndex[{}]",param);
        String res = "busiIndex_"+param;
        pushDisruptor(param);
        log.info("res BusiService.busiIndex[{}]",res);
        return res;
    }

    private void pushDisruptor(String param){
        long seq = messageBoRingBuffer.next();
        try {
            MessageBo messageBo = messageBoRingBuffer.get(seq);
            messageBo.setMsg(param);
        }finally {
            messageBoRingBuffer.publish(seq);
            log.info("Seq[{}] push to disruptor",seq);
        }
    }
}
