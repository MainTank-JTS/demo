package com.jts.demo.listener.netty.server;

import com.jts.demo.listener.netty.coder.MsgDecoder;
import com.jts.demo.listener.netty.coder.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private ApplicationContext context;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        log.info("exec ServerChannelInitializer");
        ChannelPipeline pipe = socketChannel.pipeline();
        pipe.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4));
        pipe.addLast(new MsgDecoder());
        pipe.addLast(new MsgEncoder());
//        pipe.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//        pipe.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipe.addLast(context.getBean(NettyServerHandler.class));
    }
}
