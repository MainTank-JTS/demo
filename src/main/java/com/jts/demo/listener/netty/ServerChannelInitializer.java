package com.jts.demo.listener.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipe = socketChannel.pipeline();
        pipe.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        pipe.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipe.addLast(nettyServerHandler);
    }
}
