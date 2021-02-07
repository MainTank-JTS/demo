package com.jts.demo.mq.disruptor;

import com.lmax.disruptor.EventFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageEventFactory implements EventFactory<MessageBo> {
    @Override
    public MessageBo newInstance() {
        return new MessageBo();
    }
}
