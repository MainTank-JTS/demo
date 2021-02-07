package com.jts.demo.mq.disruptor;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageEventHandler implements EventHandler<MessageBo> {
    @Override
    public void onEvent(MessageBo messageBo, long l, boolean b) throws Exception {
        if(messageBo != null ){
            log.info("Handler seq[{}],msg[{}]",l,messageBo);
        }
    }
}
