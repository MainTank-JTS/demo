package com.jts.demo.busi;

import com.jts.demo.interceptor.ImportantInterceptor;
import com.jts.demo.mq.disruptor.MessageBo;
import com.jts.demo.proxy.IUserOp;
import com.jts.demo.proxy.User;
import com.jts.demo.proxy.enhancer.UserEnhaner;
import com.jts.demo.proxy.jdk.UserJdk;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusiService {

    private final RingBuffer<MessageBo> messageBoRingBuffer;

    private UserJdk userJdk;

    private UserEnhaner userEnhaner;

    public BusiService(RingBuffer<MessageBo> messageBoRingBuffer) {
        this.messageBoRingBuffer = messageBoRingBuffer;
    }

    @Autowired
    public void setUserJdk(UserJdk userJdk) {
        this.userJdk = userJdk;
    }

    @Autowired
    public void setUserEnhaner(UserEnhaner userEnhaner) {
        this.userEnhaner = userEnhaner;
    }

    @ImportantInterceptor("getStr")
    public String busiIndex(String param){
        log.info("invocked BusiService.busiIndex[{}]",param);
        invockUserJdk().getInfo();
        invockUserEnhancer().getInfo();
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

    private IUserOp invockUserJdk(){
        userJdk.bind(new User());
        return userJdk.newUser();
    }

    private IUserOp invockUserEnhancer(){
        return userEnhaner.newUser();
    }
}
